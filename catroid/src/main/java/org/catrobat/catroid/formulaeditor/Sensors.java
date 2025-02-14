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
package org.catrobat.catroid.formulaeditor;

import org.catrobat.catroid.utils.EnumUtils;

public enum Sensors {
	X_ACCELERATION, Y_ACCELERATION, Z_ACCELERATION, COMPASS_DIRECTION, X_INCLINATION, Y_INCLINATION, LOUDNESS,
	LATITUDE, LONGITUDE, LOCATION_ACCURACY, ALTITUDE, USER_LANGUAGE,
	TIMER, DATE_YEAR, DATE_MONTH, DATE_DAY, DATE_WEEKDAY, TIME_HOUR, TIME_MINUTE, TIME_SECOND,
	FACE_DETECTED, FACE_SIZE, FACE_X, FACE_Y,
	SECOND_FACE_DETECTED, SECOND_FACE_SIZE, SECOND_FACE_X, SECOND_FACE_Y,
	NOSE_X, NOSE_Y, LEFT_EYE_INNER_X, LEFT_EYE_INNER_Y, LEFT_EYE_CENTER_X, LEFT_EYE_CENTER_Y,
	LEFT_EYE_OUTER_X, LEFT_EYE_OUTER_Y, RIGHT_EYE_INNER_X, RIGHT_EYE_INNER_Y, RIGHT_EYE_CENTER_X,
	RIGHT_EYE_CENTER_Y, RIGHT_EYE_OUTER_X, RIGHT_EYE_OUTER_Y, LEFT_EAR_X, LEFT_EAR_Y,
	RIGHT_EAR_X, RIGHT_EAR_Y, MOUTH_LEFT_CORNER_X, MOUTH_LEFT_CORNER_Y, MOUTH_RIGHT_CORNER_X,
	MOUTH_RIGHT_CORNER_Y, LEFT_SHOULDER_X, LEFT_SHOULDER_Y, RIGHT_SHOULDER_X, RIGHT_SHOULDER_Y,
	LEFT_ELBOW_X, LEFT_ELBOW_Y, RIGHT_ELBOW_X, RIGHT_ELBOW_Y, LEFT_WRIST_X, LEFT_WRIST_Y,
	RIGHT_WRIST_X, RIGHT_WRIST_Y, LEFT_PINKY_X, LEFT_PINKY_Y, RIGHT_PINKY_X,
	RIGHT_PINKY_Y, LEFT_INDEX_X, LEFT_INDEX_Y, RIGHT_INDEX_X,
	RIGHT_INDEX_Y, LEFT_THUMB_X, LEFT_THUMB_Y, RIGHT_THUMB_X,
	RIGHT_THUMB_Y, LEFT_HIP_X, LEFT_HIP_Y, RIGHT_HIP_X, RIGHT_HIP_Y, LEFT_KNEE_X,
	LEFT_KNEE_Y, RIGHT_KNEE_X, RIGHT_KNEE_Y, LEFT_ANKLE_X, LEFT_ANKLE_Y, RIGHT_ANKLE_X,
	RIGHT_ANKLE_Y, LEFT_HEEL_X, LEFT_HEEL_Y, RIGHT_HEEL_X, RIGHT_HEEL_Y,
	LEFT_FOOT_INDEX_X, LEFT_FOOT_INDEX_Y, RIGHT_FOOT_INDEX_X, RIGHT_FOOT_INDEX_Y,
	OBJECT_X(true), OBJECT_Y(true),
	OBJECT_TRANSPARENCY(true), OBJECT_BRIGHTNESS(true), OBJECT_COLOR(true), OBJECT_SIZE(true), MOTION_DIRECTION(true),
	LOOK_DIRECTION(true), OBJECT_LAYER(true), OBJECT_DISTANCE_TO(true),
	NXT_SENSOR_1, NXT_SENSOR_2, NXT_SENSOR_3, NXT_SENSOR_4,
	EV3_SENSOR_1, EV3_SENSOR_2, EV3_SENSOR_3, EV3_SENSOR_4,
	PHIRO_FRONT_LEFT, PHIRO_FRONT_RIGHT, PHIRO_SIDE_LEFT,
	PHIRO_SIDE_RIGHT, PHIRO_BOTTOM_LEFT, PHIRO_BOTTOM_RIGHT, DRONE_BATTERY_STATUS, DRONE_EMERGENCY_STATE, DRONE_FLYING,
	DRONE_INITIALIZED, DRONE_USB_ACTIVE, DRONE_USB_REMAINING_TIME, DRONE_CAMERA_READY, DRONE_RECORD_READY,
	DRONE_RECORDING, DRONE_NUM_FRAMES, COLLIDES_WITH_EDGE(true), COLLIDES_WITH_FINGER(true), OBJECT_X_VELOCITY(true),
	OBJECT_Y_VELOCITY(true), OBJECT_ANGULAR_VELOCITY(true),
	LAST_FINGER_INDEX, FINGER_X, FINGER_Y, FINGER_TOUCHED, NUMBER_CURRENT_TOUCHES,
	OBJECT_LOOK_NUMBER(true), OBJECT_LOOK_NAME(true), OBJECT_NUMBER_OF_LOOKS(true),
	OBJECT_BACKGROUND_NUMBER(true), OBJECT_BACKGROUND_NAME(true),
	NFC_TAG_ID(true), NFC_TAG_MESSAGE(true), GAMEPAD_A_PRESSED, GAMEPAD_B_PRESSED, GAMEPAD_UP_PRESSED,
	GAMEPAD_DOWN_PRESSED, GAMEPAD_LEFT_PRESSED, GAMEPAD_RIGHT_PRESSED, TEXT_FROM_CAMERA,
	TEXT_BLOCKS_NUMBER, TEXT_BLOCK_X, TEXT_BLOCK_Y,
	TEXT_BLOCK_SIZE, TEXT_BLOCK_FROM_CAMERA, TEXT_BLOCK_LANGUAGE_FROM_CAMERA, SPEECH_RECOGNITION_LANGUAGE;

	public final boolean isObjectSensor;
	public static final String TAG = Sensors.class.getSimpleName();

	Sensors(boolean isObjectSensor) {
		this.isObjectSensor = isObjectSensor;
	}

	Sensors() {
		this.isObjectSensor = false;
	}

	public static boolean isSensor(String value) {
		return EnumUtils.isValidEnum(Sensors.class, value);
	}

	public static Sensors getSensorByValue(String value) {
		return EnumUtils.getEnum(Sensors.class, value);
	}
}
