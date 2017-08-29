
angular.module('app').controller('confirmModalCtrl', confirmModalCtrl);

angular.module('app').controller('modalCtrl', modalCtrl);

confirmModalCtrl.$inject = ['$scope', '$uibModalInstance', 'parameters'];

modalCtrl.$inject = ['$scope', '$uibModalInstance','$timeout','parameters'];


function confirmModalCtrl($scope, $uibModalInstance, parameters){
    $scope.title = angular.isUndefined(parameters.title) ? '提示信息' : parameters.title;
    $scope.text = angular.isUndefined(parameters.text) ? '' : parameters.text;
    $scope.icon = angular.isUndefined(parameters.icon) ? '' : parameters.icon;
    $scope.isCancel = true;
    if (!angular.isUndefined(parameters) && parameters.onOpened != null) {
        $uibModalInstance.opened.then(function () {
            parameters.onOpened();
        });
    }
    if (!angular.isUndefined(parameters.isCancel)) {
        $scope.isCancel = parameters.isCancel;
    }
    $scope.ok = function () {
        parameters.deferred.resolve(true);
        $uibModalInstance.dismiss('cancel');
    };
    $scope.cancel = function () {
        parameters.deferred.resolve(false);
        $uibModalInstance.dismiss('cancel');
    }
}

function modalCtrl($scope,$uibModalInstance,$timeout,parameters){
    $timeout(function(){
        parameters.loadSuccess();
    })
    $scope.ok = function(){
        parameters.deferred.resolve(true);
        $uibModalInstance.dismiss('cancel');
    }
    $scope.cancel = function(){
        parameters.deferred.resolve(false);
        $uibModalInstance.dismiss('cancel');
    }
}
