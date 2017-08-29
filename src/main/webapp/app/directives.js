
angular.module('app').
directive('cityChoose', ['$timeout','$sessionStorage','citySelectService', function ($timeout,$sessionStorage,citySelectService){
    return {
        restrict: 'A',
        require: 'ngModel',
        scope:{
            cityList:"=cityList"
        },
        link: function (scope, element, attrs,ngmodel){
            citySelectService.getCityListBy($sessionStorage.CurrentUser.areaCode).then(function(data){
                scope.cityList=data;//将查询到的城市列表传递出去。用作onblur的校验。
                $(element).kuCity(function(val){
                    if(typeof val == "undefined"){
                        ngmodel.$setViewValue($(element).val());
                    }else{
                        ngmodel.$setViewValue(val);
                    }
                    scope.$apply();
                },data);
                //$(element).on("change",function(){
                //    console.log("BiuBiu!! "+$(element).val())
                //});
            });
        }
    }
}])

angular.module('app').directive('toNumber', function () {
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            if (attrs.toNumber == "money") {
                type = /[^\d{1,}\.\d{1,}|\d{1,}]/g;
            } else {
                type = /\D/g;
            }
            $(elem).on('keyup', function () {
                $(elem).val($(elem).val().replace(type, ''));
            })
            $(elem).on('afterpaste', function () {
                $(elem).val($(elem).val().replace(type, ''));
            })
            var _val;
            ctrl.$formatters.push(function (value) {
                _val = parseFloat(value || '');
                _val = isNaN(_val) || parseFloat(_val) < 0 ? '' : _val;
                if (attrs.toNumber == "money") {
                    _val = parseFloat(_val).toFixed(2);
                }
                return _val;
            });
        }
    };
});
var emoticonData = [
    { "ID": 2, "Url": "app/common/images/emoticon/0.gif", "Keyword": "/::)" },
    { "ID": 3, "Url": "app/common/images/emoticon/1.gif", "Keyword": "/::~" },
    { "ID": 4, "Url": "app/common/images/emoticon/2.gif", "Keyword": "/::B" },
    { "ID": 5, "Url": "app/common/images/emoticon/3.gif", "Keyword": "/::|" },
    { "ID": 6, "Url": "app/common/images/emoticon/4.gif", "Keyword": "/:8-)" },
    { "ID": 7, "Url": "app/common/images/emoticon/5.gif", "Keyword": "/::<" },
    { "ID": 8, "Url": "app/common/images/emoticon/6.gif", "Keyword": "/::$" },
    { "ID": 9, "Url": "app/common/images/emoticon/7.gif", "Keyword": "/::X" },
    { "ID": 10, "Url": "app/common/images/emoticon/8.gif", "Keyword": "/::Z" },
    { "ID": 11, "Url": "app/common/images/emoticon/9.gif", "Keyword": "/::'(" },
    { "ID": 12, "Url": "app/common/images/emoticon/10.gif", "Keyword": "/::-|" },
    { "ID": 13, "Url": "app/common/images/emoticon/11.gif", "Keyword": "/::@" },
    { "ID": 14, "Url": "app/common/images/emoticon/12.gif", "Keyword": "/::P" },
    { "ID": 15, "Url": "app/common/images/emoticon/13.gif", "Keyword": "/::D" },
    { "ID": 16, "Url": "app/common/images/emoticon/14.gif", "Keyword": "/::O" },
    { "ID": 17, "Url": "app/common/images/emoticon/15.gif", "Keyword": "/::(" },
    { "ID": 18, "Url": "app/common/images/emoticon/16.gif", "Keyword": "/::+" },
    { "ID": 19, "Url": "app/common/images/emoticon/17.gif", "Keyword": "/:--b" },
    { "ID": 20, "Url": "app/common/images/emoticon/18.gif", "Keyword": "/::Q" },
    { "ID": 21, "Url": "app/common/images/emoticon/19.gif", "Keyword": "/::T" },
    { "ID": 22, "Url": "app/common/images/emoticon/20.gif", "Keyword": "/:,@P" },
    { "ID": 23, "Url": "app/common/images/emoticon/21.gif", "Keyword": "/:,@-D" },
    { "ID": 24, "Url": "app/common/images/emoticon/22.gif", "Keyword": "/::d" },
    { "ID": 25, "Url": "app/common/images/emoticon/23.gif", "Keyword": "/:,@o" },
    { "ID": 26, "Url": "app/common/images/emoticon/24.gif", "Keyword": "/::g" },
    { "ID": 27, "Url": "app/common/images/emoticon/25.gif", "Keyword": "/:|-)" },
    { "ID": 28, "Url": "app/common/images/emoticon/26.gif", "Keyword": "/::!" },
    { "ID": 29, "Url": "app/common/images/emoticon/27.gif", "Keyword": "/::L" },
    { "ID": 30, "Url": "app/common/images/emoticon/28.gif", "Keyword": "/::>" },
    { "ID": 31, "Url": "app/common/images/emoticon/29.gif", "Keyword": "/::,@" },
    { "ID": 32, "Url": "app/common/images/emoticon/30.gif", "Keyword": "/:,@f" },
    { "ID": 33, "Url": "app/common/images/emoticon/31.gif", "Keyword": "/::-S" },
    { "ID": 34, "Url": "app/common/images/emoticon/32.gif", "Keyword": "/:?" },
    { "ID": 35, "Url": "app/common/images/emoticon/33.gif", "Keyword": "/:,@x" },
    { "ID": 36, "Url": "app/common/images/emoticon/34.gif", "Keyword": "/:,@@" },
    { "ID": 37, "Url": "app/common/images/emoticon/35.gif", "Keyword": "/::8" },
    { "ID": 38, "Url": "app/common/images/emoticon/36.gif", "Keyword": "/:,@!" },
    { "ID": 39, "Url": "app/common/images/emoticon/37.gif", "Keyword": "/:!!!" },
    { "ID": 40, "Url": "app/common/images/emoticon/38.gif", "Keyword": "/:xx" },
    { "ID": 41, "Url": "app/common/images/emoticon/39.gif", "Keyword": "/:bye" },
    { "ID": 42, "Url": "app/common/images/emoticon/40.gif", "Keyword": "/:wipe" },
    { "ID": 43, "Url": "app/common/images/emoticon/41.gif", "Keyword": "/:dig" },
    { "ID": 44, "Url": "app/common/images/emoticon/42.gif", "Keyword": "/:handclap" },
    { "ID": 45, "Url": "app/common/images/emoticon/43.gif", "Keyword": "/:&-(" },
    { "ID": 46, "Url": "app/common/images/emoticon/44.gif", "Keyword": "/:B-)" },
    { "ID": 47, "Url": "app/common/images/emoticon/45.gif", "Keyword": "/:<@" },
    { "ID": 48, "Url": "app/common/images/emoticon/46.gif", "Keyword": "/:@>" },
    { "ID": 49, "Url": "app/common/images/emoticon/47.gif", "Keyword": "/::-O" },
    { "ID": 50, "Url": "app/common/images/emoticon/48.gif", "Keyword": "/:>-|" },
    { "ID": 51, "Url": "app/common/images/emoticon/49.gif", "Keyword": "/:P-(" },
    { "ID": 52, "Url": "app/common/images/emoticon/50.gif", "Keyword": "/::'|" },
    { "ID": 53, "Url": "app/common/images/emoticon/51.gif", "Keyword": "/:X-)" },
    { "ID": 54, "Url": "app/common/images/emoticon/52.gif", "Keyword": "/::*" },
    { "ID": 55, "Url": "app/common/images/emoticon/53.gif", "Keyword": "/:@x" },
    { "ID": 56, "Url": "app/common/images/emoticon/54.gif", "Keyword": "/:8*" },
    { "ID": 57, "Url": "app/common/images/emoticon/55.gif", "Keyword": "/:pd" },
    { "ID": 58, "Url": "app/common/images/emoticon/56.gif", "Keyword": "/:<W>" },
    { "ID": 59, "Url": "app/common/images/emoticon/57.gif", "Keyword": "/:beer" },
    { "ID": 60, "Url": "app/common/images/emoticon/58.gif", "Keyword": "/:basketb" },
    { "ID": 61, "Url": "app/common/images/emoticon/59.gif", "Keyword": "/:oo" },
    { "ID": 62, "Url": "app/common/images/emoticon/60.gif", "Keyword": "/:coffee" },
    { "ID": 63, "Url": "app/common/images/emoticon/61.gif", "Keyword": "/:eat" },
    { "ID": 64, "Url": "app/common/images/emoticon/62.gif", "Keyword": "/:pig" },
    { "ID": 65, "Url": "app/common/images/emoticon/63.gif", "Keyword": "/:rose" },
    { "ID": 66, "Url": "app/common/images/emoticon/64.gif", "Keyword": "/:fade" },
    { "ID": 67, "Url": "app/common/images/emoticon/65.gif", "Keyword": "/:showlove" },
    { "ID": 68, "Url": "app/common/images/emoticon/66.gif", "Keyword": "/:heart" },
    { "ID": 69, "Url": "app/common/images/emoticon/67.gif", "Keyword": "/:break" },
    { "ID": 70, "Url": "app/common/images/emoticon/68.gif", "Keyword": "/:cake" },
    { "ID": 71, "Url": "app/common/images/emoticon/69.gif", "Keyword": "/:li" },
    { "ID": 72, "Url": "app/common/images/emoticon/70.gif", "Keyword": "/:bome" },
    { "ID": 73, "Url": "app/common/images/emoticon/71.gif", "Keyword": "/:kn" },
    { "ID": 74, "Url": "app/common/images/emoticon/72.gif", "Keyword": "/:footb" },
    { "ID": 75, "Url": "app/common/images/emoticon/73.gif", "Keyword": "/:ladybug" },
    { "ID": 76, "Url": "app/common/images/emoticon/74.gif", "Keyword": "/:shit" },
    { "ID": 77, "Url": "app/common/images/emoticon/75.gif", "Keyword": "/:moon" },
    { "ID": 78, "Url": "app/common/images/emoticon/76.gif", "Keyword": "/:sun" },
    { "ID": 79, "Url": "app/common/images/emoticon/77.gif", "Keyword": "/:gift" },
    { "ID": 80, "Url": "app/common/images/emoticon/78.gif", "Keyword": "/:hug" },
    { "ID": 81, "Url": "app/common/images/emoticon/79.gif", "Keyword": "/:strong" },
    { "ID": 82, "Url": "app/common/images/emoticon/80.gif", "Keyword": "/:weak" },
    { "ID": 83, "Url": "app/common/images/emoticon/81.gif", "Keyword": "/:share" },
    { "ID": 84, "Url": "app/common/images/emoticon/82.gif", "Keyword": "/:v" },
    { "ID": 85, "Url": "app/common/images/emoticon/83.gif", "Keyword": "/:@)" },
    { "ID": 86, "Url": "app/common/images/emoticon/84.gif", "Keyword": "/:jj" },
    { "ID": 87, "Url": "app/common/images/emoticon/85.gif", "Keyword": "/:@@" },
    { "ID": 88, "Url": "app/common/images/emoticon/86.gif", "Keyword": "/:bad" },
    { "ID": 89, "Url": "app/common/images/emoticon/87.gif", "Keyword": "/:lvu" },
    { "ID": 90, "Url": "app/common/images/emoticon/88.gif", "Keyword": "/:no" },
    { "ID": 91, "Url": "app/common/images/emoticon/89.gif", "Keyword": "/:ok" },
    { "ID": 92, "Url": "app/common/images/emoticon/90.gif", "Keyword": "/:love" },
    { "ID": 93, "Url": "app/common/images/emoticon/91.gif", "Keyword": "/飞吻" },
    { "ID": 94, "Url": "app/common/images/emoticon/92.gif", "Keyword": "/:jump" },
    { "ID": 95, "Url": "app/common/images/emoticon/93.gif", "Keyword": "/:shake"},
    { "ID": 96, "Url": "app/common/images/emoticon/94.gif", "Keyword": "/怄火" },
    { "ID": 97, "Url": "app/common/images/emoticon/95.gif", "Keyword": "/:circle"},
    { "ID": 98, "Url": "app/common/images/emoticon/96.gif", "Keyword": "/:kotow" },
    { "ID": 99, "Url": "app/common/images/emoticon/97.gif", "Keyword": "/:turn" },
    { "ID": 100, "Url": "app/common/images/emoticon/98.gif", "Keyword": "/:skip" },
    { "ID": 101, "Url": "app/common/images/emoticon/99.gif", "Keyword": "/:oY" },
    { "ID": 102, "Url": "app/common/images/emoticon/100.gif", "Keyword": "/激动" },
    { "ID": 103, "Url": "app/common/images/emoticon/101.gif", "Keyword": "/街舞" },
    { "ID": 104, "Url": "app/common/images/emoticon/102.gif", "Keyword": "/献吻" },
    { "ID": 105, "Url": "app/common/images/emoticon/103.gif", "Keyword": "/左太极" },
    { "ID": 106, "Url": "app/common/images/emoticon/104.gif", "Keyword": "/右太极" }
]
angular.module('app').directive('emoticon', function () {
    return {
        restrict: 'E',
        templateUrl: 'app/common/template/emotions.tpl.html',
        scope: {
            setText: "=",
            welcomeEmotion: "="
        },
        link: function (scope, element, attrs) {
            scope.toggleEmoticon = function (e) {
                $(".emotion_wrp").toggle();
            };
            scope.chooseIcon = function (index) {
                scope.setText = (angular.isUndefined(scope.setText)||scope.setText==null ? "" : scope.setText) + "<img src=\"" + scope.row[index].Url + "\">";
                //scope.weixinMessage = scope.weixinMessage + scope.row[index].Keyword
            };
            document.onclick = function (event) {
                var e = event || window.event;
                var targetId = $(e.target).attr("id");
                //var elem = e.srcElement;
                if (targetId == "emotion-preview-link") {
                    return;
                } else {
                    $(".emotion_wrp").hide();
                }
            };
            scope.row = emoticonData;
        }
    };
});

angular.module("app").directive("minLength", function(){
    return{
        restrict:"A",
        require:"ngModel",
        link:function(scope, element, attrs, ctrl){
            ctrl.$parsers.push(function(viewValue){
                if(viewValue.length >= attrs.minLength){
                    ctrl.$setValidity("minlength",true);
                }else{
                    ctrl.$setValidity("minlength",false);
                }
                return viewValue;
            });
        }
    };
});
angular.module('app').directive('setFocus', function () {
    return function (scope, element, attr) {
        scope.$watch(attr.setFocus, function (newValue, oldValue) {
            console.log(newValue);
            if (newValue)
                element[0].focus();
        });
    };
});
angular.module('app').directive('loading', function ($timeout) {
    return {
        restrict: 'E',
        replace: true,
        transclude: true,
        template: '<div>' +
        '<div ng-transclude></div>' +
        '<div id="loading_btn" class="loading" ng-class="{\'hidden\':!flag}">' +
        '<div class="loader-inner ball-beat">' +
        '<div></div>' +
        '<div></div>' +
        '<div></div>' +
        '</div>' +
        '</div>' +
        '</div>',
        link: function (scope, element, attr) {
            scope.$watch(attr.show, function (newValue, oldValue) {
                scope.flag = newValue;
                $timeout(function () {
                    scope.$apply(attr.show);
                });
            });
        }
    };
});