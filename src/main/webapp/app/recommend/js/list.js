angular.module('recommend').controller('recommendCtrl', function (title, $scope, $http, $state, $sessionStorage) {
    title.setTitle('我的推荐');
    $scope.loadingFlag = true;
    $scope.notData = false;
    $scope.param = {};
    $scope.dataList = [];
    $scope.onInit = function () {
        $scope.loadingFlag = true;
        $scope.dataList = [];
        $http.post(ctx + '/recommend/list', $scope.param).success(function (resp) {
            if (resp.successful) {
                $scope.dataList = resp.data;
                $scope.loadingFlag = false;
                $scope.notData = false;
                if (!$scope.dataList || $scope.dataList.length == 0) $scope.notData = true;
            } else {
                console.log(resp.errorMessage);
            }
        });


    };
    $scope.onInit();
    $scope.gotoAddPage = function () {
        $http.get(ctx + '/evalute/getMemberFromDB').success(function (resp) {
            if (resp.successful) {
                $sessionStorage.membership = resp.data;
            }
            if (!$sessionStorage.membership || !$sessionStorage.membership.phone || !$sessionStorage.membership.openId) {
                $state.go('app.auth', {state: 'app.recommend', sendType: 0});
                return;
            }
            $state.go('app.selectModule',{
                jump:'app.recommend-add',//添加完物品，要跳转的页面
                cancel:'app.recommend-list'//物品列表页面，取消按钮要跳转的页面
            });
        });
    };
    $scope.gotoIndex = function () {
        $state.go('app.home');
    };
    $scope.detail = function (id) {
        $state.go('app.recommend-detail', {id: id});
    };




});

function DatepickerPopupDemoCtrl($scope)
{
    $scope.today = function()
    {
        $scope.dt = new Date();
    };
    $scope.today();
    $scope.clear = function()
    {
        $scope.dt = null;
    };
    $scope.inlineOptions = {
        customClass: getDayClass,
        minDate: new Date(),
        showWeeks: true
    };
    $scope.dateOptions = {
        dateDisabled: disabled,
        formatYear: 'yy',
        maxDate: new Date(2020, 5, 22),
        minDate: new Date(),
        startingDay: 1
    };
    // Disable weekend selection
    function disabled(data)
    {
        var date = data.date,
            mode = data.mode;
        return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
    }
    $scope.toggleMin = function()
    {
        $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
        $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
    };
    $scope.toggleMin();
    $scope.open1 = function()
    {
        $scope.popup1.opened = true;
    };
    $scope.open2 = function()
    {
        $scope.popup2.opened = true;
    };
    $scope.setDate = function(year, month, day)
    {
        $scope.dt = new Date(year, month, day);
    };
    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[0];
    $scope.altInputFormats = ['M!/d!/yyyy'];
    $scope.popup1 = {
        opened: false
    };
    $scope.popup2 = {
        opened: false
    };
    var tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    var afterTomorrow = new Date();
    afterTomorrow.setDate(tomorrow.getDate() + 1);
    $scope.events = [
        {
            date: tomorrow,
            status: 'full'
        },
        {
            date: afterTomorrow,
            status: 'partially'
        }];

    function getDayClass(data)
    {
        var date = data.date,
            mode = data.mode;
        if (mode === 'day')
        {
            var dayToCheck = new Date(date).setHours(0, 0, 0, 0);
            for (var i = 0; i < $scope.events.length; i++)
            {
                var currentDay = new Date($scope.events[i].date).setHours(0, 0, 0, 0);
                if (dayToCheck === currentDay)
                {
                    return $scope.events[i].status;
                }
            }
        }
        return '';
    }
}