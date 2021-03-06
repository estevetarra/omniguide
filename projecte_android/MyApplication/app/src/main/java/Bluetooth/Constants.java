package Bluetooth;

import android.content.Context;
import android.support.design.BuildConfig;

public final class Constants
{
    public static final String LOG_TAG = "MiBandSetting";

    public static final boolean IS_LOGGABLE = BuildConfig.DEBUG;
    public static final boolean IS_PARAMETER_CHECKING_ENABLED = BuildConfig.DEBUG;

    public static int getVersionCode(final Context context)
    {
        if (Constants.IS_PARAMETER_CHECKING_ENABLED)
        {
            if (null == context)
            {
                throw new IllegalArgumentException("context cannot be null");
            }
        }

        try
        {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }
        catch (final UnsupportedOperationException e)
        {
            return 1;
        }
        catch (final Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    private Constants() {
        throw new UnsupportedOperationException("This class is non-instantiable"); //$NON-NLS-1$
    }
}