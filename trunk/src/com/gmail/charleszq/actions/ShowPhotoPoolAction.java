/**
 * 
 */
package com.gmail.charleszq.actions;

import android.app.Activity;

import com.aetrion.flickr.photos.PhotoPlace;
import com.gmail.charleszq.FlickrViewerApplication;
import com.gmail.charleszq.R;
import com.gmail.charleszq.dataprovider.PaginationPhotoListDataProvider;
import com.gmail.charleszq.dataprovider.PhotoPoolDataProvider;
import com.gmail.charleszq.task.AsyncPhotoListTask;

/**
 * @author qiangz
 * 
 */
public class ShowPhotoPoolAction extends ActivityAwareAction {

	private PhotoPlace mPhotoPlace;
	private boolean mClearStack = false;

	/**
	 * @param activity
	 */
	public ShowPhotoPoolAction(Activity activity, PhotoPlace photoPlace) {
		this(activity,photoPlace,false);
	}
	
	public ShowPhotoPoolAction(Activity activity, PhotoPlace photoPlace, boolean clearStack) {
		super(activity);
		this.mPhotoPlace = photoPlace;
		this.mClearStack = clearStack;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gmail.charleszq.actions.IAction#execute()
	 */
	@Override
	public void execute() {
		FlickrViewerApplication app = (FlickrViewerApplication) mActivity
				.getApplication();
		final PaginationPhotoListDataProvider photoListDataProvider = new PhotoPoolDataProvider(mPhotoPlace);
		photoListDataProvider.setPageSize(app.getPageSize());
		final AsyncPhotoListTask task = new AsyncPhotoListTask(mActivity,
				photoListDataProvider, null, mActivity.getResources()
						.getString(R.string.task_loading_photo_pool), mClearStack);
		task.execute();
	}

}
