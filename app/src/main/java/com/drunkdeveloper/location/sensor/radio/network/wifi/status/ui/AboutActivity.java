/*
 * Copyright © 2013–2016 Michael von Glasow.
 *
 * This file is part of LSRN Tools.
 *
 * LSRN Tools is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LSRN Tools is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LSRN Tools.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.drunkdeveloper.location.sensor.radio.network.wifi.status.ui;

import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.drunkdeveloper.location.sensor.radio.network.wifi.status.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        TextView aboutBuild = (TextView) findViewById(R.id.aboutBuild);

        InputStream buildInStream = getResources().openRawResource(R.raw.build);
        ByteArrayOutputStream buildOutStream = new ByteArrayOutputStream();

        int i;
        try {
            i = buildInStream.read();
            while (i != -1) {
                if (i >= 32) buildOutStream.write(i);
                i = buildInStream.read();
            }
            buildInStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            aboutBuild.setText(String.format("%s %s (%s)", this.getString(R.string.about_version), getPackageManager().getPackageInfo(getPackageName(), 0).versionName, buildOutStream.toString()));
        } catch (PackageManager.NameNotFoundException e) {
            aboutBuild.setText(buildOutStream.toString());
        }

        TextView aboutText = (TextView) findViewById(R.id.aboutText);
        aboutText.setText(Html.fromHtml(this.getString(R.string.about_text)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
