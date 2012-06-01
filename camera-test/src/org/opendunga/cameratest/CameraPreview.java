package org.opendunga.cameratest;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
	private SurfaceHolder holder;
	protected Camera camera;
	
	public CameraPreview(Context context) {
		super(context);
		holder = getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	public void surfaceCreated(SurfaceHolder holder) {
		camera = Camera.open();
		try {
			camera.setPreviewDisplay(holder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		camera.stopPreview();
		Camera.Parameters params = camera.getParameters();
		params.setPreviewSize(width, height);
		camera.setParameters(params);
		camera.startPreview();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		camera.stopPreview();
		camera.release();
	}
}
