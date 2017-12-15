/*
 * MIT License
 *
 * Copyright (c) 2017 Amir Maimon
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gitfib.apps.launchrandom;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;

/*
* Launch a random game
* */
public class LaunchGame extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Intent> gameList = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        Intent launchIntent;

        for (ResolveInfo app : getPackageManager().queryIntentActivities(intent, 0))
            if (app.activityInfo != null) {
                launchIntent = getPackageManager().getLaunchIntentForPackage(app.activityInfo.packageName);
                if (    (app.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_IS_GAME) == ApplicationInfo.FLAG_IS_GAME ||
                        (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && app.activityInfo.applicationInfo.category == ApplicationInfo.CATEGORY_GAME))
                    gameList.add(launchIntent);
            }

        startActivity(gameList.get((int)(Math.random() * gameList.size())));
        finish();
    }
}
