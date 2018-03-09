package geb.mobile.android.activities

import geb.mobile.android.AndroidBaseActivity
import groovy.util.logging.Slf4j

@Slf4j
class ViewAllPhotosActivity extends AndroidBaseActivity {

    static content = {

        selectPhotoButton
        selectSinglePhotoButtons ---> should be an array
        mainDeleteButton
        confirmDeletePhotosButton

        backToDetailsButton
    }



}
