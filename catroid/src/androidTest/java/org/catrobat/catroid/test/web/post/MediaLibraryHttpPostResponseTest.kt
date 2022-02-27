/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2022 The Catrobat Team
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

package org.catrobat.catroid.test.web

import androidx.test.ext.junit.runners.AndroidJUnit4
import okhttp3.OkHttpClient
import okhttp3.Response
import org.junit.experimental.categories.Category
import org.catrobat.catroid.testsuites.annotations.Cat.OutgoingNetworkTest
import org.catrobat.catroid.common.FlavoredConstants.LIBRARY_OBJECT_URL
import org.catrobat.catroid.web.PostWebConnection
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CompletableFuture

@Category(OutgoingNetworkTest::class)
@RunWith(AndroidJUnit4::class)
class MediaLibraryHttpPostResponseTest : PostWebConnection.PostWebRequestListener {

    private val Headers = "User-Agent:Mozilla/5.0 (compatible; Catrobatbot/1.0; " +
        "+https://catrob.at/bot) accept:application/json"
    private val Data = "Sample text"
    private val okHttpClient = OkHttpClient()
    private lateinit var response: CompletableFuture<String>

    companion object {
        private const val HTML_RESPONSE_START = "<!DOCTYPE html>"
        private const val HTML_RESPONSE_END = "</html>"
    }

    @Before
    fun setup() {
        response = CompletableFuture()
    }

    @Test
    fun testHttp() {
        PostWebConnection(okHttpClient, this, LIBRARY_OBJECT_URL, Headers, Data
        ).sendPostWebRequest()
        val body: String = response.get().trim()
        Assert.assertTrue(body.startsWith(HTML_RESPONSE_START))
        Assert.assertTrue(body.endsWith(HTML_RESPONSE_END))
    }

    override fun onRequestSuccess(httpResponse: Response) {
        response.complete(httpResponse.body()?.string() ?: "")
    }

    override fun onRequestError(httpError: String) {
        response.complete(httpError)
    }

    override fun onCancelledCall() {
        response.complete("")
    }

}