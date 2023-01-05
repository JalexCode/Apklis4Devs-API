package cu.jalexcode.apklis4devs.api.utils;

import cu.jalexcode.apklis4devs.api.model.Application;

public final class Constants {
    Constants() {
    }

    public static final String TYPE_DAY = "day";
    public static final String TYPE_MONTH = "month";
    public static final String AUTHORIZATION = "7UMqh5p9CPPPEPi0s4Ksrv2vR5tBjBJH8XlaNMMB";
    public static final String HASH = "c67a0f957126ab92ca340f6e9c6c40f95b6899e4c1100310fa5ca9a493ee3002cc1181a58ed247ed9905ead64486d033d000f7743ac8b2dea40cb133aec6a1f1";
    public static final String URL_API_HOST = "https://api.apklis.cu";
    public static final String URL_API_CATEGORIES = "https://api.apklis.cu/v1/category/";

    public static final class V2 {

        V2() {
        }


        public static final String URL_API_MONTH_DOWNLOADS = URL_API_HOST + "/v2/download/by_months/";

        public static final String URL_API_APP_FORMAT = URL_API_HOST + "/v2/application/%s";

        public static final String URL_API_APP_FORMAT(String arg) {
            return String.format(URL_API_APP_FORMAT, arg);
        }

        public static final String URL_API_DEV_FORMAT = URL_API_HOST + "/v2/application/developer/?developer=%s";

        public static final String URL_API_DEV_FORMAT(String arg) {
            return String.format(URL_API_DEV_FORMAT, arg);
        }

        public static final String URL_API_GLOBALS_DASHBOARD_STATS = "https://api.apklis.cu/v2/download/dashboard_stats/";
        public static final String URL_API_GLOBALS_DASHBOARD_DOWNLOADS = "https://api.apklis.cu/v2/download/dashboard_download/";

        public static final String URL_API_RELEASES = "https://api.apklis.cu/v2/download/by_releases/";
    }

    public static final class V1 {
        public static final String URL_API_TOKEN_DEV = "https://api.apklis.cu/o/token";
        public static final String URL_API_REGISTER = "https://api.apklis.cu/v1/developer/";

        public static final String URL_API_USER_INFO = URL_API_HOST + "/v1/user/me/";
        public static final String URL_API_USER_INFO_FORMAT = "https://api.apklis.cu/v1/user/%s/";

        public static final String URL_API_USER_INFO(String val) {
            return String.format(URL_API_USER_INFO_FORMAT, val);
        }

        public static final String URL_API_DEV_INFO_FORMAT = "https://api.apklis.cu/v1/developer/%s/";

        public static final String URL_API_DEV_INFO(String val) {
            return String.format(URL_API_DEV_INFO_FORMAT, val);
        }

        public static final String URL_API_APPS_BY_OWNER = "https://api.apklis.cu/v1/application/?owner=%s";

        public static final String URL_API_APPS_BY_OWNER(int id) {
            return String.format(URL_API_APPS_BY_OWNER, id);
        }

        public static final String URL_API_CHANGE_USER_INFO_FORMAT = "https://api.apklis.cu/v1/user/%s/";

        public static final String URL_API_CHANGE_USER_INFO(String user) {
            return String.format(URL_API_CHANGE_USER_INFO_FORMAT, user);
        }

        public static final String URL_API_CHANGE_APP_DATA_FORMAT = "https://api.apklis.cu/v1/application/%s/";

        public static final String URL_API_CHANGE_APP_DATA(String arg) {
            return String.format(URL_API_CHANGE_APP_DATA_FORMAT, arg);
        }

        public static final String URL_API_SEE_REVIEWS = "https://api.apklis.cu/v1/review/?search=&limit=10&offset=%s&application=%s&ordering=-published";

        public static final String URL_API_SEE_REVIEWS(int last, Application app) {
            return String.format(URL_API_SEE_REVIEWS, last * 10 + "", app.getId() + "");
        }

        public static final String URL_API_REVIEW = "https://api.apklis.cu/v1/review/%s/";

        public static final String URL_API_REVIEW(int id) {
            return String.format(URL_API_REVIEW, id + "");
        }

        public static final String URL_API_REPLY = URL_API_HOST + "/v2/application/send_reply/";

        public static final String URL_API_PUSH_APP = URL_API_HOST + "/v1/release";

        public static final String URL_API_SALES = URL_API_HOST + "/v1/payment/sales/?lte=%s&gte=%s&seller=%s&type=%s"; //day

        public static final String URL_API_SALES(String date1, String date2, String phoneNumber, String type) {
            return String.format(URL_API_SALES, date2, date1, phoneNumber, type);
        }

        /*
        Obtener reporte de ventas
         */
        public static final String URL_SALES_REPORT = URL_API_HOST + "/v1/payment/?date__gte=%s&date__lte=%s&limit=%d&seller=%s&offset=0";

        public static final String URL_SALES_REPORT(String date1, String date2, int limit, String phoneNumber) {
            return String.format(URL_SALES_REPORT, date2, date1, limit, phoneNumber);
        }

        public static final String URL_NOTIFICATIONS = URL_API_HOST + "/v1/notification/?hash=%s";

        public static final String URL_NOTIFICATIONS(String hash) {
            return String.format(URL_NOTIFICATIONS, hash);
        }
    }
}