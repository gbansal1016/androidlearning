package com.example.android.boardingpass;

/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.boardingpass.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        BoardingPassInfo boardingPassInfo = FakeDataUtils.generateFakeBoardingPassInfo();
        bindViewObjects(boardingPassInfo);
    }

    private void bindViewObjects(BoardingPassInfo boardingPassInfo) {
        mActivityBinding.textViewFlightCode.setText(boardingPassInfo.flightCode);
        mActivityBinding.boardingTime.setText(boardingPassInfo.boardingTime.toString());
        mActivityBinding.arrivalAirport.setText(boardingPassInfo.destCode);
    }

}
