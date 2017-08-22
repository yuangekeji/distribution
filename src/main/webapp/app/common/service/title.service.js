angular.module('app').service('title', function () {
    /**
     * 解决IOS平台微信浏览器改变TITLE失败的问题
     * @param title
     */
    var page_title = function (title) {
        var body = document.getElementsByTagName('body')[0];
        document.title = title;
        var iframe = document.createElement("iframe");
        // iframe.setAttribute("src", ctx + "/resources/images/icon_close.svg");
        iframe.addEventListener('load', function () {
            setTimeout(function () {
                try {
                    iframe.removeEventListener('load');
                } catch (error) {

                }
                document.body.removeChild(iframe);
            }, 0);
        });
        try {
            document.body.appendChild(iframe);
        } catch (error) {
        }
    }
    return {
        setTitle: function (title) {
            page_title(title);
        }
    }
});