var app = angular.module('myapp', ['ui.router', 'ngMaterial']);


/**
 * Konfiguracia aplikacneho modulu Angular
 * @param {state} $stateProvider poskytuje odkaz na stranku
 * 
 */
app.config(function ($stateProvider, $urlRouterProvider, $httpProvider) {
    $urlRouterProvider.otherwise('/loginUvod');

    $stateProvider
            .state('adminChoices', {
                url: '/adminChoices',
                templateUrl: 'adminChoice.html'
            })
            .state('loginUvod', {
                url: '/loginUvod',
                controller: 'loginController',
                controllerAs: 'loginCtrl', 
                templateUrl: 'loginUvod.html'
            })
            .state('adminSenzory', {
                url: '/adminSenzory',
                controller: 'adminSenzoryCtrl',
                controllerAs: 'adminSCtrl', 
                templateUrl: 'adminSenzory.html'
            })
            .state('adminUzivatelia', {
                url: '/adminUzivatelia',
                controller: 'adminUzivateliaCtrl',
                controllerAs: 'adminUzCtrl', 
                templateUrl: 'adminUzivatelia.html'
            })
            .state('prihlasenyUzivatel', {
                url: '/prihlasenyUzivatel',
                controller: 'prihlasUzivatelCtrl',
                controllerAs: 'prihlasUziCtrl', 
                templateUrl: 'prihlasenyUzivatel.html'
            })
        
            $httpProvider.defaults.useXDomain = true;
            delete $httpProvider.defaults.headers.common['X-Requested-With'];
});

app.controller('productController', function ($scope) {
var modal = document.getElementById('loginPrihlasenie');
var modal1 = document.getElementById('loginRegistracia');
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
    if (event.target == modal1) {
        modal1.style.display = "none";
    }
}
});

app.controller('adminController', function ($scope, $http) {

});

app.controller('loginController', function ($scope, $http, $location, $rootScope) {
$scope.dajMeno = function(meno, password){
    $scope.base_url = 'http://localhost:8080/TestRestful/webresources/user/getUserByName?name='+meno;
    $http.get($scope.base_url ).success(function(response){
        $scope.result = response.user;
       console.log(response.user);
       $rootScope.user_id = response.idUser;
       $rootScope.user_meno = response.name;
       console.log($rootScope.user_meno);
         if (response.heslo == password && angular.equals(response.name, "administrator")){
            $location.path("/adminChoices");
            console.log("admin OK");
        }
        else if (response.heslo == password && response.meno != "administrator"){
            $location.path("/prihlasenyUzivatel");
            console.log("uzivatel OK");
        }else{
                alert("Zadal si nespravne meno alebo heslo");
            }
    }); 
};
$scope.checkDajMeno = function(meno, password){
    if(!angular.isUndefined(meno) && !angular.isUndefined(password)){
        $scope.dajMeno(meno, password);
    }else{
            alert("chybaju udaje");
        
    }
};
$scope.registruj = function(meno, pohlavie, adresa, heslo){
    $scope.url_reg_user = 'http://localhost:8080/TestRestful/webresources/user/saveDataUser';
    var data = {"idUser": "0", "name": meno, "gender": pohlavie, "address": adresa, "heslo": heslo};
    console.log(data);
    $http.post($scope.url_reg_user, data).success(function (response) {
        console.log("user registrovany");
    });
};

$scope.checkRegistruj = function (meno, pohlavie, adresa, heslo){
    console.log(meno);
    console.log(pohlavie);
    console.log(adresa);
    console.log(angular.isUndefined(meno));
    if(!angular.isUndefined(meno) && !angular.isUndefined(pohlavie) && !angular.isUndefined(adresa) && !angular.isUndefined(heslo)){
        console.log("dobre je");
        document.getElementById('loginRegistracia').style.display = "none";
        $scope.registruj(meno, pohlavie, adresa, heslo);
    }else{
            alert("chybaju udaje");
        console.log("chyba");
    }
}

$scope.genders = ["M", "Z"];

var modal = document.getElementById('loginPrihlasenie');
var modal1 = document.getElementById('loginRegistracia');
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
    if (event.target == modal1) {
        modal1.style.display = "none";
    }
}
});

app.controller('adminSenzoryCtrl', function ($scope, $http, $timeout, $interval) {
var modal = document.getElementById('id01');
var modal1 = document.getElementById('hodnoty');
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
    if (event.target == modal1) {
        modal1.style.display = "none";
    }
}
    console.log($scope.hodnota);
    
    $scope.reload = function () {
        $scope.url_sensor = 'http://localhost:8080/TestRestful/webresources/sensor/getSensorList';
        $http.get($scope.url_sensor).success(function (response) {
            $scope.result_sensor = response.sensor;
            //console.log($scope.result);
            //console.log(response);
        });
    }

    $scope.pridaj = function (nazov, typ) {
        if(!angular.isUndefined(nazov) && !angular.isUndefined(typ)){
        console.log($scope.typ);
        $scope.url_sensor = 'http://localhost:8080/TestRestful/webresources/sensor/saveDataSensor';
        // idUser; name; gender; address;
        var data = {"idSensor": "0", "name": nazov, "type": typ};
        $http.post($scope.url_sensor, data).success(function (response) {
        });
        
        $timeout(function() { $scope.reload();}, 200);
         document.getElementById('id01').style.display = "none";
        console.log("presla obnova");
    }else{
         alert("chybaju udaje");
    }
    };
    
    $scope.reload();
    
    $scope.zmazSenzor = function(sensorID){
        $scope.zmaz_sensor = 'http://localhost:8080/TestRestful/webresources/sensor/deleteSensor?idSensor=' + sensorID;
        $http.get($scope.zmaz_sensor).success(function (response) {
           console.log("Senzor zmazany");
        });
        $timeout(function() { $scope.reload();}, 200);
        console.log("presla obnova");
    };
    
    $scope.SwitchFuction = function (typ) {
        switch (typ) {
            case 'teplota':
                $scope.premenna_typ = 'Teplota [°C]';
                break;
            case 'rychlost_vetra':
                $scope.premenna_typ = 'Rýchlosť vetra [m/s]';
                break;
            case 'vlhkost':
                $scope.premenna_typ = 'Vlhkosť [%]';
                break;
            case 'tlak':
                $scope.premenna_typ = 'Tlak [hPa]';
                break;
            case 'prasnost':
                $scope.premenna_typ = 'Prašnosť [%]';
                break;
            default:
                console.log("default");
        }
    };
    
    $scope.nacitajHodnoty = function (nazovSenzora, typ){
        console.log(typ);
        $scope.SwitchFuction(typ);
        $scope.special_typ = typ;
        $scope.nazov_senzor = nazovSenzora;
        $scope.url_hodnoty = 'http://localhost:8080/TestRestful/webresources/value/getValueByType?type=' + typ;
        $http.get($scope.url_hodnoty ).success(function(response){
            $scope.value1 = response.value1;
            $scope.value2 = response.value2;
            $scope.value3 = response.value3;
            $scope.value4 = response.value4;
            $scope.value5 = response.value5;
            $scope.value6 = response.value6;
            $scope.value7 = response.value7;
            $scope.value8 = response.value8;         
            $scope.value9 = response.value9;
            $scope.value10 = response.value10;
            
            $scope.dajGraf($scope.premenna_typ);
             console.log("hodnoty nacitane");
        });
    };
    
    $scope.dajGraf = function (premenna){
        var ctx = document.getElementById("myChart").getContext('2d');
       
        var myChart = new Chart(ctx, {
            height:200,
            type: 'line',
            
            data: {
                labels: ["Hodnota 1", "Hodnota 2", "Hodnota 3", "Hodnota 4", "Hodnota 5",
                        "Hodnota 6", "Hodnota 7", "Hodnota 8", "Hodnota 9", "Hodnota 10"],
                datasets: [{
                    label: premenna,
                    data: [$scope.value1, $scope.value2, $scope.value3, $scope.value4, $scope.value5,
                            $scope.value6, $scope.value7, $scope.value8, $scope.value9, $scope.value10],
                    backgroundColor: null,
                    borderColor: [
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:false
                        }
                    }]
                }
            }
        });
    }
    
    $scope.names = ["teplota", "rychlost_vetra", "vlhkost", "tlak", "prasnost"];
    
    var myInterval1 = $interval(function() { $scope.nacitajHodnoty($scope.nazov_senzor, $scope.special_typ);}, 30000);
    $scope.$on('$destroy', function(){
        $interval.cancel(myInterval1);
    });
});

app.controller('adminUzivateliaCtrl', function ($scope, $http, $timeout) {
    $scope.reloadAdUz = function(){
        $scope.url_user = 'http://localhost:8080/TestRestful/webresources/user/getUserList' ;
        $http.get($scope.url_user ).success(function(response){
            $scope.result_user = response.user;
        });
        console.log("Uzivatelia nacitani");
    };
    $scope.reloadAdUz();
    
    $scope.zmazUzivatela = function (idUzivatel) {
        if (idUzivatel != 1){
        $scope.url_sensor = 'http://localhost:8080/TestRestful/webresources/user/deleteUser?idUser=' + idUzivatel;
        $http.get($scope.url_sensor).success(function (response) {
           console.log("Uzivatel zmazany");
        });
        $timeout(function() { $scope.reloadAdUz();}, 200);
        console.log("presla obnova");
        }else{
            alert("Administrator nemôže byť zmazaný");
        }
    };
});

app.controller('prihlasUzivatelCtrl', function ($scope, $http, $rootScope, $interval, $timeout) {
    $scope.user_id_data = $rootScope.user_id;
    $scope.user_meno_data = $rootScope.user_meno;
    console.log($scope.user_gender_data);
    $scope.reloadUzivatelHodnoty = function(){
    $scope.base_url = 'http://localhost:8080/TestRestful/webresources/sensor/getSensorList' ;
                $http.get($scope.base_url ).success(function(response){
                    $scope.result = response.sensor;
                    console.log("obnova presla");
                });
    }       
            
    $scope.dajUzivatela = function (id){
        console.log(id);
        $scope.url_uzivatel = 'http://localhost:8080/TestRestful/webresources/user/getUserById?idUser=' + id;
            $http.get($scope.url_uzivatel).success(function(response){
            $scope.uzivatel_data = response.user;
            $scope.uzivatel_gender_data = response.gender;
            console.log("uzivatel sa nacital");
            document.getElementById('meno').value = response.name;
            document.getElementById('adresa').value = response.address;
            document.getElementById('heslo').value = response.heslo;
        });
    };
    
    $scope.posliUzivatelaDoDB = function (){
        console.log($scope.user_id_data);
        console.log(!angular.equals(document.getElementById('meno').value,"") );
        if(!angular.equals(document.getElementById('meno').value,"") 
            && !angular.equals(document.getElementById('adresa').value,"") 
            && !angular.equals(document.getElementById('heslo').value,""))
        {     
        $scope.meno = document.getElementById('meno').value;
        $scope.adresa = document.getElementById('adresa').value;
        $scope.heslo = document.getElementById('heslo').value;
        console.log($scope.meno);
        console.log($scope.user_gender_data);
        console.log($scope.adresa);
        console.log($scope.heslo);
        $scope.url_uzivatel_do_DB = 'http://localhost:8080/TestRestful/webresources/user/updateName?idUser='+$scope.user_id_data+'&name='+$scope.meno+'&gender='+$scope.uzivatel_gender_data+'&address='+$scope.adresa+'&heslo='+$scope.heslo;
            console.log($scope.url_uzivatel_do_DB);
                $http.put($scope.url_uzivatel_do_DB).success(function (response) {
                });
        console.log("presla uprava uzivatela");
        document.getElementById('profil').style.display = "none";
    }else{
        alert("chybaju udaje");
    }
    };
    
    $scope.SwitchFuction = function (typ) {
        switch (typ) {
            case 'teplota':
                $scope.premenna_typ = 'Teplota [°C]';
                break;
            case 'rychlost_vetra':
                $scope.premenna_typ = 'Rýchlosť vetra [m/s]';
                break;
            case 'vlhkost':
                $scope.premenna_typ = 'Vlhkosť [%]';
                break;
            case 'tlak':
                $scope.premenna_typ = 'Tlak [hPa]';
                break;
            case 'prasnost':
                $scope.premenna_typ = 'Prašnosť [%]';
                break;
            default:
                console.log("default");
        }
    };
    
    $scope.nacitajHodnoty = function (nazovSenzora, typ){
        console.log(typ);
        $scope.SwitchFuction(typ);
        
        $scope.special_typ = typ;
        $scope.nazov_senzor = nazovSenzora;
        $scope.url_hodnoty = 'http://localhost:8080/TestRestful/webresources/value/getValueByType?type=' + typ;
        $http.get($scope.url_hodnoty ).success(function(response){
            $scope.value1 = response.value1;
            $scope.value2 = response.value2;
            $scope.value3 = response.value3;
            $scope.value4 = response.value4;
            $scope.value5 = response.value5;
            $scope.value6 = response.value6;
            $scope.value7 = response.value7;
            $scope.value8 = response.value8;         
            $scope.value9 = response.value9;
            $scope.value10 = response.value10;
            
            $scope.dajGraf($scope.premenna_typ);
            console.log("hodnoty nacitane");
        });
    };
    
    $scope.dajGraf = function (premenna){
        console.log(premenna);
        var ctx = document.getElementById("myChart").getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: ["Hodnota 1", "Hodnota 2", "Hodnota 3", "Hodnota 4", "Hodnota 5",
                        "Hodnota 6", "Hodnota 7", "Hodnota 8", "Hodnota 9", "Hodnota 10"],
                datasets: [{
                    label: premenna,
                    data: [$scope.value1, $scope.value2, $scope.value3, $scope.value4, $scope.value5,
                            $scope.value6, $scope.value7, $scope.value8, $scope.value9, $scope.value10],
                    backgroundColor: null,
                    
                    borderColor: [
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:false
                        }
                    }]
                }
            }
        });
    }
    
    $scope.names = ["teplota", "rychlost_vetra", "vlhkost", "tlak", "prasnost"];

    $scope.reloadUzivatelHodnoty();
    var myInterval = $interval(function() { $scope.nacitajHodnoty($scope.nazov_senzor, $scope.special_typ);}, 30000);
    $scope.$on('$destroy', function(){
        $interval.cancel(myInterval);
    });
            
    var modal = document.getElementById('profil');
    var modal1 = document.getElementById('hodnoty1');
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
            console.log("vyplo sa");
        }
        if (event.target == modal1) {
            modal1.style.display = "none";
            console.log("vyplo sa");
        }
    }
});

