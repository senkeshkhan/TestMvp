/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package empolyesecurity.testmvp.data;






//import empolyesecurity.testmvp.data.db.DbHelper;
import empolyesecurity.testmvp.data.network.APIService;
import empolyesecurity.testmvp.data.network.ApiHelper;
import empolyesecurity.testmvp.data.prefs.PreferencesHelper;

/**
 * Created by janisharali on 27/01/17.
 */

public interface DataManager extends ApiHelper,PreferencesHelper,APIService {

    void updateApiHeader(Long userId, String accessToken);

    void setUserAsLoggedOut();

   // Observable<Boolean> seedDatabaseQuestions();

  //  Observable<Boolean> seedDatabaseOptions();

    void updateUserInfo(
            String accessToken,
            Long userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String profilePicPath);

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}