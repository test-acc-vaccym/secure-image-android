package geb.mobile.android.activities

import geb.mobile.android.AndroidBaseActivity
import groovy.util.logging.Slf4j

// This is a sample:


@Slf4j
class WikisearchActivity extends AndroidBaseActivity {

    static content = {
        searchButton {find("~Search Wikipedia")}
        searchText {$("#search_src_text")}
        resultList {$("android.widget.TextView")}
        targetResult {$("//android.widget.TextView[@text='HyperCard']")}

        swipeAction {
            if(!targetResult) {
                swipeDown()
            } else {
                back()
                return true
            }
        }
    }
}
