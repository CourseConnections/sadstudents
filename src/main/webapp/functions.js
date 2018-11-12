/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//google callback. This function will redirect to our login servlet
function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    console.log('ID: ' + profile.getId());
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail());
    console.log('id_token: ' + googleUser.getAuthResponse().id_token);
    
    //do not post all above info to the server because that is not secure.
    //just send the id_token
    
    var redirectUrl = 'account?action=login';
    
    //using jquery to post data dynamically
    var form = $('<form action="' + redirectUrl + '" method="post">' +
            '<input type="hidden" name="id_token" value="' +
            googleUser.getAuthResponse().id_token + '" />' +
            '</form>');
    $('body').append(form);
    form.submit();
}

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
}

function addCourse() {
    
    alert("Clicked!");
    
    
    var request = new XMLHttpRequest();
    request.open("GET", "https://courseconnections.rocket.chat/api/v1/commands.run", true);
    request.send();
    
    
    $.ajax({
        type: "GET",
        url: "https://courseconnections.rocket.chat/api/v1/commands.run",
        data: {'command':invite,'roomId':sXBYJjvCpJ3EtGmAY,'params':"@jamie.gachie"},
        headers: '{"X-Auth-Token":"bUj7z12R_NZRFdYGrolW10IMsm-4Bot8D-DXM36ZkSp","X-User-ID":"initialuser","Content-type":"application/json"}',
        contentType: "application/json",
        beforeSend: function(request) {
            request.setRequestHeader("X-Auth-Token", "bUj7z12R_NZRFdYGrolW10IMsm-4Bot8D-DXM36ZkSp");
            request.setRequestHeader("X-User-ID", "initialuser");
            request.setRequestHeader("Content-type", "application/json");
        },
        success: function(data) {
            console.log(data);
            //do something when request is successfull
        },
        dataType: "json"
    });
}