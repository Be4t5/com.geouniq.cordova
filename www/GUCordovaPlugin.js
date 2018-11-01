var exec = require('cordova/exec');

exports.enable = function (callback) {
    exec(
        callback,
        null,
        'GUCordovaPlugin',
        'enable',
        []
    );
};

exports.disable = function (callback) {
    exec(
        callback,
        null,
        'GUCordovaPlugin',
        'disable',
        []
    );
};

exports.showConsentDialogAndSet = function (callback) {
    exec(
        callback,
        null,
        'GUCordovaPlugin',
        'showConsentDialogAndSet',
        []
    );
};

exports.setConsentStatus = function (status, callback) {
    exec(
        callback,
        null,
        'GUCordovaPlugin',
        'setConsentStatus',
        [status]
    );
};

exports.getConsentStatus = function (callback) {
    exec(
        callback,
        null,
        'GUCordovaPlugin',
        'getConsentStatus',
        []
    );
};

exports.setDeviceIdListener = function (callback) {
    exec(
        callback,
        null,
        'GUCordovaPlugin',
        'setDeviceIdListener',
        []
    );
};

exports.getDeviceId = function (callback) {
    exec(
        callback,
        null,
        'GUCordovaPlugin',
        'getDeviceId',
        []
    );
};

exports.isDeviceIdAvailable = function (callback) {
    exec(
        callback,
        null,
        'GUCordovaPlugin',
        'isDeviceIdAvailable',
        []
    );
};

exports.setCustomId = function (customId, callback) {
    exec(
        callback,
        null,
        'GUCordovaPlugin',
        'setCustomId',
        [customId]
    );
};

exports.solveIssues = function (callback) {
    exec(
        callback,
        null,
        'GUCordovaPlugin',
        'solveIssues',
        []
    );
};

exports.testDialog = function (title, message, callbackSuccess, callbackError) {
    exec(
        callbackSuccess,
        callbackError,
        'GUCordovaPlugin',
        'testDialog',
        [title, message]
    );
};