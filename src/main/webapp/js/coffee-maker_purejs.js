/*jslint browser: true*/
/*global $, jQuery*/
$(document).ajaxError(function (jqXHR, settings, errorThrown) {
    alert('Error: ' + settings.url + ": " + errorThrown);
});

$(document).ready(function () {
    'use strict';
    var servletURL = 'coffeemaker';
    $('button[name="action"]').click(function () {
        var $button = $(this);
        var reqData = {
            action: $button.val()
        };
        console.log("----- Button pressed: " + $button.val());

        if ($button.val() !== "pressOnOff") {
            $button.toggleClass('button-off button-on');
        }
        if ($button.parents('div#main-buttons').length) {
            $('#display-area').text('Working on ' + $button.val());
        } else {
            $('#display-area').text('Serving ' + $button.val());
        }
        var getJSON = function (url, data, successHandler, errorHandler) {
            var queryString = "?action=" + data.action;  //doPost of servlet is not able to handel JSON data, must url-encode
            var xhrReq = new XMLHttpRequest();
            xhrReq.open('POST', url + queryString, true);
            xhrReq.responseType = 'json';
            xhrReq.onload = function () {
                var status = xhrReq.status;
                if (status === 200) {
                    successHandler && successHandler(xhrReq.response);
                } else {
                    errorHandler && errorHandler(status);
                }
            };
            xhrReq.send();
        };

        getJSON(servletURL, reqData, function (data) {
            //console.log(data[0]);
            console.log(data.coffeesServed);
            $('#display-area').text('').append('Coffees served: ' + data.coffeesServed + ' | ');
            $('#display-area').append('Water: ' + data.water + ' | ');
            $('#display-area').append('Beans: ' + data.beans);

            $button.toggleClass('button-off button-on');

            if (!data.on) {
                $('#display-area').text("Coffee maker is turned off");
            }

        }, function (status) {
            alert('Something went wrong.');
        });

        /**
         $.ajax({
         method: 'POST',
         url: 'coffeemaker',
         data: reqData,
         dataType: 'json', //Type of data returned by the server
         success: function (data) {
         console.log("Data 1: " + data.powerOn);
         processResponse($button, data);
         },
         error: function (jqXHR, textStatus, errorThrown) {
         alert('Error: ' + textStatus + ": " + errorThrown);
         }
         });
         **/

    });
});


