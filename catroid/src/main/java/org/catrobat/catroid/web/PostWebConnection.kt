/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2021 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid.web

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Headers
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.Response
import org.catrobat.catroid.common.Constants
import org.catrobat.catroid.stage.StageActivity
import java.io.IOException
import java.lang.ref.WeakReference

class PostWebConnection(
    private val okHttpClient: OkHttpClient,
    listener: PostWebRequestListener,
    private val url: String,
    private val header: String,
    private val data: String
) {
    private var weakListenerReference: WeakReference<PostWebRequestListener>? = WeakReference(listener)
    private var call: Call? = null

    constructor(listener: PostWebRequestListener, url: String, header: String, data: String) :
        this(StageActivity.stageListener.postWebConnectionHolder.okHttpClient, listener, url, header, data)

    companion object {
        private const val EXCEPTION_MESSAGE_TIMEOUT = "timeout"
        private const val EXCEPTION_MESSAGE_CANCELED = "Canceled"
    }

    @Synchronized
    private fun popListener(): PostWebRequestListener? {
        val listener = weakListenerReference?.get()
        weakListenerReference = null
        return listener
    }

    @Synchronized
    fun sendPostWebRequest() {
        try {
            val headerBuilder = Headers.Builder()
                .add("User-Agent", Constants.USER_AGENT)
            val headerLines = header.lines()
            headerLines.forEach {
                if (it.startsWith("user-agent: ", ignoreCase = true)) {
                    headerBuilder.set("User-Agent", it.substring("user-agent: ".length))
                } else {
                    headerBuilder.add(it)
                }
            }
            val headers = headerBuilder.build()
            val mediaType = MediaType.parse(headers.get("Content-Type") ?: "text/plain")
            val request = Request.Builder()
                .url(url)
                .headers(headers)
                .post(RequestBody.create(mediaType, data))
                .build()
            call = okHttpClient.newCall(request)
            call?.enqueue(createCallback())
        } catch (exception: IllegalArgumentException) {
            weakListenerReference?.get()?.onRequestError(Constants.ERROR_BAD_REQUEST.toString())
            Log.e(javaClass.simpleName, "Invalid URL", exception)
        }
    }

    private fun createCallback(): Callback {
        return object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                popListener()?.apply {
                    when (e.message) {
                        EXCEPTION_MESSAGE_TIMEOUT -> onRequestError(Constants.ERROR_TIMEOUT.toString())
                        EXCEPTION_MESSAGE_CANCELED -> onCancelledCall()
                        else -> onRequestError(Constants.ERROR_SERVER_ERROR.toString())
                    }
                }
            }

            override fun onResponse(call: Call, response: Response) {
                popListener()?.apply {
                    if (response.isSuccessful) {
                        onRequestSuccess(response)
                    } else {
                        onRequestError(response.code().toString())
                    }
                }
            }
        }
    }

    fun cancelCall() {
        okHttpClient.dispatcher()?.executorService()?.execute {
            call?.cancel()
        }
    }

    interface PostWebRequestListener {
        fun onRequestSuccess(httpResponse: Response)
        fun onRequestError(httpError: String)
        fun onCancelledCall()
    }
}