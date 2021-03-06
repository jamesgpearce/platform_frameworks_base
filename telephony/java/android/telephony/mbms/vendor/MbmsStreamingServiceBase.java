/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package android.telephony.mbms.vendor;

import android.annotation.Nullable;
import android.net.Uri;
import android.os.RemoteException;
import android.telephony.mbms.IMbmsStreamingManagerCallback;
import android.telephony.mbms.IStreamingServiceCallback;
import android.telephony.mbms.MbmsException;

import java.util.List;

/**
 * @hide
 * TODO: future systemapi
 */
public class MbmsStreamingServiceBase extends IMbmsStreamingService.Stub {
    /**
     * Initialize streaming service for this app and subId, registering the listener.
     *
     * May throw an {@link IllegalArgumentException} or a {@link SecurityException}
     *
     * @param listener The callback to use to communicate with the app.
     * @param appName The app name as negotiated with the wireless carrier.
     * @param subscriptionId The subscription ID to use.
     * @return {@link MbmsException#SUCCESS} or {@link MbmsException#ERROR_ALREADY_INITIALIZED}
     */
    @Override
    public int initialize(IMbmsStreamingManagerCallback listener, String appName,
            int subscriptionId) throws RemoteException {
        return 0;
    }

    /**
     * Registers serviceClasses of interest with the appName/subId key.
     * Starts async fetching data on streaming services of matching classes to be reported
     * later via {@link IMbmsStreamingManagerCallback#streamingServicesUpdated(List)}
     *
     * Note that subsequent calls with the same uid, appName and subId will replace
     * the service class list.
     *
     * May throw an {@link IllegalArgumentException} or an {@link IllegalStateException}
     *
     * @param appName The app name as negotiated with the wireless carrier.
     * @param subscriptionId The subscription id to use.
     * @param serviceClasses The service classes that the app wishes to get info on. The strings
     *                       may contain arbitrary data as negotiated between the app and the
     *                       carrier.
     * @return One of {@link MbmsException#SUCCESS},
     *         {@link MbmsException#ERROR_MIDDLEWARE_NOT_BOUND},
     *         {@link MbmsException#ERROR_CONCURRENT_SERVICE_LIMIT_REACHED}
     */
    @Override
    public int getStreamingServices(String appName, int subscriptionId,
            List<String> serviceClasses) throws RemoteException {
        return 0;
    }

    /**
     * Starts streaming on a particular service. This method may perform asynchronous work. When
     * the middleware is ready to send bits to the frontend, it should inform the app via
     * {@link IStreamingServiceCallback#streamStateChanged(int)}.
     *
     * May throw an {@link IllegalArgumentException} or an {@link IllegalStateException}
     *
     * @param appName The app name as negotiated with the wireless carrier.
     * @param subscriptionId The subscription id to use.
     * @param serviceId The ID of the streaming service that the app has requested.
     * @param listener The listener object on which the app wishes to receive updates.
     * @return {@link MbmsException#SUCCESS}, {@link MbmsException#ERROR_STREAM_ALREADY_STARTED},
     *         or {@link MbmsException#ERROR_UNABLE_TO_START_SERVICE}.
     */
    @Override
    public int startStreaming(String appName, int subscriptionId,
            String serviceId, IStreamingServiceCallback listener) throws RemoteException {
        return 0;
    }

    /**
     * Retrieves the streaming URI for a particular service. If the middleware is not yet ready to
     * stream the service, this method may return null.
     *
     * May throw an {@link IllegalArgumentException} or an {@link IllegalStateException}
     *
     * @param appName The app name as negotiated with the wireless carrier.
     * @param subscriptionId The subscription id to use.
     * @param serviceId The ID of the streaming service that the app has requested.
     * @return An opaque {@link Uri} to be passed to a video player that understands the format.
     */
    @Override
    public @Nullable Uri getPlaybackUri(String appName, int subscriptionId, String serviceId)
            throws RemoteException {
        return null;
    }

    /**
     * Stop streaming the stream identified by {@code serviceId}. Notification of the resulting
     * stream state change should be reported to the app via
     * {@link IStreamingServiceCallback#streamStateChanged(int)}.
     *
     * May throw an {@link IllegalArgumentException} or an {@link IllegalStateException}
     *
     * @param appName The app name as negotiated with the wireless carrier.
     * @param subscriptionId The subscription id to use.
     * @param serviceId The ID of the streaming service that the app wishes to stop.
     */
    @Override
    public void stopStreaming(String appName, int subscriptionId, String serviceId)
            throws RemoteException {
    }

    /**
     * Dispose of the stream identified by {@code serviceId} for the app identified by the
     * {@code appName} and {@code subscriptionId} arguments along with the caller's uid.
     * No notification back to the app is required for this operation, and the callback provided via
     * {@link #startStreaming(String, int, String, IStreamingServiceCallback)} should no longer be
     * used after this method has called by the app.
     *
     * May throw an {@link IllegalArgumentException} or an {@link IllegalStateException}
     *
     * @param appName The app name as negotiated with the wireless carrier.
     * @param subscriptionId The subscription id to use.
     * @param serviceId The ID of the streaming service that the app wishes to dispose of.
     */
    @Override
    public void disposeStream(String appName, int subscriptionId, String serviceId)
            throws RemoteException {
    }

    /**
     * Signals that the app wishes to dispose of the session identified by the {@code appName} and
     * {@code subscriptionId} arguments, as well as the caller's uid. No notification back to the
     * app is required for this operation, and the corresponding callback provided via
     * {@link #initialize(IMbmsStreamingManagerCallback, String, int)} should no longer be used
     * after this method has been called by the app.
     *
     * May throw an {@link IllegalStateException}
     *
     * @param appName The app name as negotiated with the wireless carrier.
     * @param subscriptionId The subscription id to use.
     */
    @Override
    public void dispose(String appName, int subscriptionId) throws RemoteException {
    }
}
