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

        $.post(servletURL, reqData, function (data) {
            $('#display-area').text('').append('Coffees served: ' + data.coffeesServed + ' | ');
            $('#display-area').append('Water: ' + data.water + ' | ');
            $('#display-area').append('Beans: ' + data.beans);

            $button.toggleClass('button-off button-on');

            if (!data.on) {
                $('#display-area').text("Coffee maker is turned off");
            }
        },
                'json' //Also the default: Intelligent giess will work normally
                );

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


