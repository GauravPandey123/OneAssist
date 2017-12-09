package app.androidhive.info.realm;

import android.content.Context;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by sheelu on 6/10/15.
 */

public class AppImageDisplayOptions {
    // DIO -Display Image Options



    public static DisplayImageOptions getCachedInDiskAndDelayDIO(){
        //Cached images in disk...
        return new DisplayImageOptions.Builder().
                cacheOnDisk(true).
                resetViewBeforeLoading(true)
                .delayBeforeLoading(200).build();
    }
    public static DisplayImageOptions getDefaultCachedInMemoryDIO(){
        //cache images in memory ram...
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .resetViewBeforeLoading(true).
                        build();
    }

    public static void clearCacheMemory() {
        //to clear memory cache...
        ImageLoader.getInstance().clearMemoryCache();
    }

    /*  Image UIL Libaray Configuartion*/
    public static void setUILImageLoadingConfig(Context context) {
        if (!ImageLoader.getInstance().isInited()) {
            DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                    .resetViewBeforeLoading(true)
                    .cacheOnDisk(true).cacheInMemory(true).build();

            // Create global configuration and initialize ImageLoader with this config
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                    .threadPoolSize(3)
                    .threadPriority(Thread.NORM_PRIORITY - 2) // default
                    .tasksProcessingOrder(QueueProcessingType.FIFO)
                    .defaultDisplayImageOptions(defaultOptions) // default
                    .build();
            ImageLoader.getInstance().init(config);
        }
    }

}
