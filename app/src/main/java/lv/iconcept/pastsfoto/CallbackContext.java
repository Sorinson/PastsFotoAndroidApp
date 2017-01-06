package lv.iconcept.pastsfoto;

/**
 * Created by iconcept on 1/6/2017.
 */
import org.json.JSONArray;
import android.view.View;

import org.json.JSONObject;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class CallbackContext {
    private static final String LOG_TAG = "CordovaPlugin";

    private String callbackId;
    private View webView;
    protected boolean finished;
    private int changingThreads;

    public CallbackContext(String callbackId, View webView) {
        this.callbackId = callbackId;
        this.webView = webView;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isChangingThreads() {
        return changingThreads > 0;
    }

    public String getCallbackId() {
        return callbackId;
    }

    public void sendPluginResult(PluginResult pluginResult) {
        synchronized (this) {
            if (finished) {
                //LOG.w(LOG_TAG, "Attempted to send a second callback for ID: " + callbackId + "\nResult was: " + pluginResult.getMessage());
                return;
            } else {
                finished = !pluginResult.getKeepCallback();
            }
        }
        //webView.sendPluginResult(pluginResult, callbackId);
    }

    /**
     * Helper for success callbacks that just returns the Status.OK by default
     *
     * @param message           The message to add to the success result.
     */
    public void success(JSONObject message) {
        sendPluginResult(new PluginResult(PluginResult.Status.OK, message));
    }

    /**
     * Helper for success callbacks that just returns the Status.OK by default
     *
     * @param message           The message to add to the success result.
     */
    public void success(String message) {
        sendPluginResult(new PluginResult(PluginResult.Status.OK, message));
    }

    /**
     * Helper for success callbacks that just returns the Status.OK by default
     *
     * @param message           The message to add to the success result.
     */
    public void success(JSONArray message) {
        sendPluginResult(new PluginResult(PluginResult.Status.OK, message));
    }

    /**
     * Helper for success callbacks that just returns the Status.OK by default
     *
     * @param message           The message to add to the success result.
     */
    public void success(byte[] message) {
        sendPluginResult(new PluginResult(PluginResult.Status.OK, message));
    }

    /**
     * Helper for success callbacks that just returns the Status.OK by default
     *
     * @param message           The message to add to the success result.
     */
    public void success(int message) {
        sendPluginResult(new PluginResult(PluginResult.Status.OK, message));
    }

    /**
     * Helper for success callbacks that just returns the Status.OK by default
     */
    public void success() {
        sendPluginResult(new PluginResult(PluginResult.Status.OK));
    }

    /**
     * Helper for error callbacks that just returns the Status.ERROR by default
     *
     * @param message           The message to add to the error result.
     */
    public void error(JSONObject message) {
        sendPluginResult(new PluginResult(PluginResult.Status.ERROR, message));
    }

    /**
     * Helper for error callbacks that just returns the Status.ERROR by default
     *
     * @param message           The message to add to the error result.
     */
    public void error(String message) {
        sendPluginResult(new PluginResult(PluginResult.Status.ERROR, message));
    }

    /**
     * Helper for error callbacks that just returns the Status.ERROR by default
     *
     * @param message           The message to add to the error result.
     */
    public void error(int message) {
        sendPluginResult(new PluginResult(PluginResult.Status.ERROR, message));
    }
}