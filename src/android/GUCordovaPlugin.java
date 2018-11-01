package com.geouniq.cordova;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.geouniq.android.GeoUniq;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GUCordovaPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if(action.equals("enable")){
			this.enable(callbackContext);
			return true;
		}
		else if(action.equals("disable")){
			this.disable(callbackContext);
                return true;
		}
		else if(action.equals("showConsentDialogAndSet")){
			this.showConsentDialogAndSet(callbackContext);
                return true;
		}
		else if(action.equals("setConsentStatus")){
			this.setConsentStatus(args.getBoolean(0), callbackContext);
                return true;
		}
		else if(action.equals("getConsentStatus")){
			this.getConsentStatus(callbackContext);
                return true;
		}
		else if(action.equals("setDeviceIdListener")){
			this.setDeviceIdListener(callbackContext);
			return true;
		}
		else if(action.equals("getDeviceId")){
			this.getDeviceId(callbackContext);
                return true;
		}
		else if(action.equals("isDeviceIdAvailable")){
			this.isDeviceIdAvailable(callbackContext);
                return true;
		}
		else if(action.equals("setCustomId")){
			this.setCustomId(args.getString(0), callbackContext);
                return true;
		}
		else if(action.equals("solveIssues")){
			this.solveIssues(callbackContext);
                return true;
		}

        return false;
    }

    private void enable(CallbackContext callbackContext) {
        GeoUniq mGeoUniq = GeoUniq.getInstance(cordova.getActivity());
        mGeoUniq.enable();
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
    }

    private void disable(CallbackContext callbackContext) {
        GeoUniq mGeoUniq = GeoUniq.getInstance(cordova.getActivity());
        mGeoUniq.disable();
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
    }

    private void showConsentDialogAndSet(final CallbackContext callbackContext) {
        GeoUniq.getInstance(cordova.getActivity()).showConsentDialogAndSet(cordova.getActivity(), new GeoUniq.IConsentAlertResponseListener() {
            @Override
            public void onResponse(boolean granted) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, granted));
            }
        });
    }

    private void setConsentStatus(boolean status, CallbackContext callbackContext) {
        GeoUniq.getInstance(cordova.getActivity()).setConsentStatus(status);
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
    }

    private void getConsentStatus(CallbackContext callbackContext) {
        boolean consentStatus = GeoUniq.getInstance(cordova.getActivity()).getConsentStatus();
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, consentStatus));
    }

    private void setDeviceIdListener(final CallbackContext callbackContext) {
        GeoUniq.getInstance(cordova.getActivity()).setDeviceIdListener(new GeoUniq.IDeviceIdListener() {
            @Override
            public void onDeviceIdAvailable(String deviceID) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, deviceID));
            }
        });
    }

    private void getDeviceId(final CallbackContext callbackContext) {
        String deviceId = GeoUniq.getInstance(cordova.getActivity()).getDeviceId();
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, deviceId));
    }

    private void isDeviceIdAvailable(final CallbackContext callbackContext) {
        boolean deviceIdAvailable = GeoUniq.getInstance(cordova.getActivity()).isDeviceIdAvailable();
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, deviceIdAvailable));
    }

    private void setCustomId(final String customId, final CallbackContext callbackContext) throws JSONException {
        final boolean isSet = GeoUniq.getInstance(cordova.getActivity()).setCustomId(customId);
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, new JSONObject()
                .put("customId", customId)
                .put("isSet", isSet))
        );
    }

    private void solveIssues(final CallbackContext callbackContext) {
        GeoUniq.getInstance(cordova.getActivity()).setErrorListener(new GeoUniq.IErrorListener() {
            @Override
            public void onError(GeoUniq.RequestResult requestResult) {
                if (requestResult.hasResolution()) {
                    requestResult.startResolution(cordova.getActivity());
                }
            }
        });
    }

    private void testDialog(String title, String message, final CallbackContext callbackContext) {
        new AlertDialog.Builder(cordova.getActivity())
                .setTitle(title)
                .setMessage(message)
                //.setTitle(cordova.getActivity().getResources().getIdentifier("geouniq_mobile_key", "string", cordova.getActivity().getPackageName()))
                //.setMessage(cordova.getActivity().getResources().getIdentifier("geouniq_mobile_key", "string", cordova.getActivity().getPackageName()))
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
}
