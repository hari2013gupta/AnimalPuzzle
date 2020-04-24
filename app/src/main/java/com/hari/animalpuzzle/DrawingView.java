package com.hari.animalpuzzle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

class DrawingView extends SurfaceView implements SurfaceHolder.Callback {
	class PuzzleThread extends Thread {
		public static final int DIF_EAS = 0;

		public static final int STATE_LOSE = 1, STATE_PAUSE = 2, STATE_READY = 3, STATE_RUNNING = 4, STATE_WIN = 5;
		private static final String KEY_DIF = "mDif";
		private int screenH = 1,screenW = 1;
		private int mDif;
		private long mLastTime;
		private int mMode;

		private boolean mRun = false;

		private SurfaceHolder mSurfaceHolder;

		private Handler mHandler;

		public PuzzleThread(SurfaceHolder surfaceHolder, Context context,
				Handler handler) {

			mSurfaceHolder = surfaceHolder;
			mHandler = handler;
			mContext = context;
		}

		public void doStart() {
			synchronized (mSurfaceHolder) {
				numberOfPieces = 9;

				if (mDif == DIF_EAS) {
					numberOfPieces = 9;
				} else if (mDif == DIF_EAS) {
					numberOfPieces = 9;
				}

				setState(STATE_RUNNING);
			}
		}


		public void pause() {
			synchronized (mSurfaceHolder) {
				if (mMode == STATE_RUNNING)
					setState(STATE_PAUSE);
			}
		}

		public synchronized void restoreState(Bundle savedState) {
			synchronized (mSurfaceHolder) {
				setState(STATE_PAUSE);
				mDif = savedState.getInt(KEY_DIF);
			}
		}

		@Override
		public void run() {
			while (mRun) {
				Canvas c = null;
				try {
					c = mSurfaceHolder.lockCanvas(null);
					synchronized (mSurfaceHolder) {
						if (mMode == STATE_RUNNING)
							updatePhysics();
						doDraw(c);
					}
				}
				catch(Exception ex){ex.printStackTrace();}
				finally {
					if (c != null) {
						mSurfaceHolder.unlockCanvasAndPost(c);
					}
				}
			}
		}

		public Bundle saveState(Bundle map) {
			synchronized (mSurfaceHolder) {
				if (map != null) {
					map.putInt(KEY_DIF, Integer.valueOf(mDif));
				}
			}
			return map;
		}

		public void setdif(int dif) {
			synchronized (mSurfaceHolder) {
				mDif = dif;
			}
		}

		public void setRunning(boolean b) {
			mRun = b;
		}

		public void setState(int mode) {
			synchronized (mSurfaceHolder) {
				setState(mode, null);
			}
		}

		public void setState(int mode, CharSequence message) {
			synchronized (mSurfaceHolder) {
				mMode = mode;
				if (mMode == STATE_RUNNING) {
					Message msg = mHandler.obtainMessage();
					Bundle b = new Bundle();
					b.putString("text", "");
					b.putInt("viz", View.INVISIBLE);
					msg.setData(b);
					mHandler.sendMessage(msg);
				} else {
					Resources res = mContext.getResources();
					CharSequence str = "";
					if (mMode == STATE_READY)
						str = res.getText(R.string.mode_ready);
					else if (mMode == STATE_PAUSE)
						str = res.getText(R.string.mode_pause);
					else if (mMode == STATE_LOSE)
						str = res.getText(R.string.mode_lose);
					else if (mMode == STATE_WIN)
						str = res.getString(R.string.mode_win_prefix)
								+ res.getString(R.string.mode_win_suffix);

					if (message != null) {
						str = message + "\n" + str;
					}

					Message msg = mHandler.obtainMessage();
					Bundle b = new Bundle();
					b.putString("text", str.toString());
					b.putInt("viz", View.VISIBLE);
					msg.setData(b);
					mHandler.sendMessage(msg);
				}
			}
		}

		public SurfaceHolder getSurfaceHolder() {
			return mSurfaceHolder;
		}

		public void setSurfaceSize(int width, int height) {
			synchronized (mSurfaceHolder) {
				screenW = width;
				screenH = height;

			}
		}

		public void unpause() {
			// Move the real time clock up to now
			synchronized (mSurfaceHolder) {
				mLastTime = System.currentTimeMillis() + 100;
			}
			setState(STATE_RUNNING);
		}

		private void doDraw(Canvas canvas) {

			if (!physicsInitialized) {
				rand = new Random();

				int n;
				while (jumbled.size() < 9) {
					n = rand.nextInt(9);
					while (jumbled.contains(n)) {
						n = rand.nextInt(9);
					}
					jumbled.add(n);

				}

				for (int i = 0; i < numberOfPieces; i++) {
					puzzlePieces[i] = new PuzzlePiece();
				}
				for (int i = 0; i < numberOfPieces; i++) {
					puzzleSlots[i] = new PuzzleSlot();
				}

				image = Bitmap.createScaledBitmap(image, screenW, screenH,
						false);
				int w = image.getWidth();
				int h = image.getHeight();
				bitmapWd3 = w / 3;
				bitmapHd3 = h / 3;
				System.out.println("-----------------Width======"+bitmapWd3);
				System.out.println("-----------------Height======"+bitmapHd3);
				
				int x, y;
				for (int i = 0; i < 9; i++) {

					if (i < 3) {
						y = 0;
					} else if (i < 6) {
						y = bitmapHd3;
					} else {
						y = bitmapHd3 * 2;
					}

					x = (i % 3) * bitmapWd3;

					puzzlePieces[i].bitmap = Bitmap.createBitmap(image, x, y,
							bitmapWd3, bitmapHd3);

					puzzlePieces[i].px = x;
					puzzlePieces[i].px2 = x + bitmapWd3;

					puzzlePieces[i].py = y;
					puzzlePieces[i].py2 = y + bitmapHd3;

					puzzleSlots[i].sx = x;
					puzzleSlots[i].sx2 = x + bitmapWd3;

					puzzleSlots[i].sy = y;
					puzzleSlots[i].sy2 = y + bitmapHd3;

					puzzleSlots[i].puzzlePiece = puzzlePieces[i];

					puzzleSlots[i].slotNum = puzzleSlots[i].puzzlePiece.pieceNum = i;
				}

				jumblePicture();

				physicsInitialized = true;
			}

			canvas.drawColor(Color.BLACK);
			for (int i = 0; i < numberOfPieces; i++) {
				canvas.drawBitmap(puzzleSlots[i].puzzlePiece.bitmap,
						puzzleSlots[i].puzzlePiece.px,
						puzzleSlots[i].puzzlePiece.py, null);
			}
			if (movingPiece) {
				canvas.drawBitmap(
						puzzleSlots[currPieceOnTouch].puzzlePiece.bitmap,
						puzzleSlots[currPieceOnTouch].puzzlePiece.px,
						puzzleSlots[currPieceOnTouch].puzzlePiece.py, null);
			}

			canvas.save();
			canvas.restore();
		}

		private void jumblePicture() {
			for (int i = 0; i < numberOfPieces; i++) {
				int oldslot = i;
				int newslot = jumbled.get(i);
				if (oldslot != newslot) {
					PuzzlePiece temp = new PuzzlePiece();
					temp = puzzleSlots[oldslot].puzzlePiece;
					puzzleSlots[oldslot].puzzlePiece = puzzleSlots[newslot].puzzlePiece;
					puzzleSlots[oldslot].puzzlePiece.px = puzzleSlots[oldslot].sx;
					puzzleSlots[oldslot].puzzlePiece.py = puzzleSlots[oldslot].sy;
					puzzleSlots[newslot].puzzlePiece = temp;
					puzzleSlots[newslot].puzzlePiece.px = puzzleSlots[newslot].sx;
					puzzleSlots[newslot].puzzlePiece.py = puzzleSlots[newslot].sy;
					temp = null;
				}
			}
		}

		private void updatePhysics() {
		}
	}

	private Context mContext;

	private TextView mStatusText;
	private Button mSaveButton;

	private PuzzleThread thread;

	private PuzzlePiece[] puzzlePieces = new PuzzlePiece[9];
	private PuzzleSlot[] puzzleSlots = new PuzzleSlot[9];

	private Bitmap image;

	private int bitmapWd3 = 0;
	private int bitmapHd3 = 0;

	private int numberOfPieces = 9;
	private int currPieceOnTouch;
	private int currSlotOnTouchUp;
	public int imageNumber;
	
	public int imagenum=imageNumber;
	private boolean movingPiece;
	private boolean mExternalStorageAvailable, mExternalStorageWriteable;

	private ArrayList<Integer> jumbled = new ArrayList<Integer>();

	private Random rand;
	private boolean physicsInitialized, solved;

	private Toast toast;

	private int[] pics = { R.drawable.a0, R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15, R.drawable.a16, R.drawable.a17, R.drawable.a18, R.drawable.a19,  R.drawable.a20, 
			};
	
	private Resources res;

	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);

		SurfaceHolder holder = getHolder();
		holder.addCallback(this);

		thread = new PuzzleThread(holder, context, new Handler() {
			@Override
			public void handleMessage(Message m) {
				mStatusText.setVisibility(m.getData().getInt("viz"));
				mStatusText.setText(m.getData().getString("text"));
			}
		});

		res = context.getResources();
		rand = new Random();
		imageNumber = rand.nextInt(pics.length);
		System.out.println("---------------image no000000000000000--------//"+imageNumber);
		image = BitmapFactory.decodeResource(res, pics[imageNumber]);

		setFocusable(true); 
	}

	public PuzzleThread getThread() {
		return thread;
	}
/////////////////////////////hari_seperation_---------
	public void showBtnForNextLevel() {
		mSaveButton.setVisibility(INVISIBLE);
		
		rand = new Random();
		imageNumber = rand.nextInt(pics.length);
		image = BitmapFactory.decodeResource(res, pics[imageNumber]);
		System.out.println("---------------image no000000000000000--------//"+imageNumber);

		physicsInitialized = false;
	}
	public void saveImage() {
//			mSaveButton.setVisibility(INVISIBLE);
		
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			mExternalStorageAvailable = true;
			mExternalStorageWriteable = false;
		} else {
			mExternalStorageAvailable = mExternalStorageWriteable = false;
		}

		if (mExternalStorageAvailable && mExternalStorageWriteable) {
			File path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			String name = "";
			switch (imageNumber) {

			case 0:
				name = "a0.jpg";
				break;
				
			case 1:
				name="a1.jpg";
				break;
					
			case 2:
				name="a2.jpg";
				break;
					
			case 3:
				name = "a3.jpg";
				break;
			
			case 4:
				name="a4.jpg";
				break;
				
			case 5:
				name = "a5.jpg";
				break;
			
			case 6:
				name="a6.jpg";
				break;
				
			case 7:
				name = "a7.jpg";
				break;
			
			case 8:
				name="a8.jpg";
				break;
				
			case 9:
				name = "a9.jpg";
				break;
			
			case 10:
				name="a10.jpg";
				break;
				
			case 11:
				name = "a11.jpg";
				break;
			
			case 12:
				name="a12.jpg";
				break;
				
			case 13:
				name = "a13.jpg";
				break;
			
			case 14:
				name="a14.jpg";
				break;
				
			case 15:
				name = "a15.jpg";
				break;
			
			case 16:
				name="a16.jpg";
				break;
				
			case 17:
				name = "a17.jpg";
				break;
			
			case 18:
				name="a18.jpg";
				break;
				
			case 19:
				name = "a19.jpg";
				break;
			
			case 20:
				name="a20.jpg";
				break;
				
			}

			File file = new File(path, name);
			InputStream is = null;

			try {
				boolean b1 = path.mkdirs();
				boolean b2 = path.exists();
				if (b1|| b2) {

					switch (imageNumber) {
					case 0:
						is = getResources().openRawResource(R.drawable.a0);
						break;
					case 1:
						is = getResources().openRawResource(R.drawable.a1);
						break;
					case 2:
						is = getResources().openRawResource(R.drawable.a2);
						break;
					case 3:
						is = getResources().openRawResource(R.drawable.a3);
						break;
					case 4:
						is = getResources().openRawResource(R.drawable.a4);
						break;
					case 5:
						is = getResources().openRawResource(R.drawable.a5);
						break;
					case 6:
						is = getResources().openRawResource(R.drawable.a6);
						break;
					case 7:
						is = getResources().openRawResource(R.drawable.a7);
						break;
					case 8:
						is = getResources().openRawResource(R.drawable.a8);
						break;
					case 9:
						is = getResources().openRawResource(R.drawable.a9);
						break;
					case 10:
						is = getResources().openRawResource(R.drawable.a10);
						break;
					case 11:
						is = getResources().openRawResource(R.drawable.a11);
						break;
					case 12:
						is = getResources().openRawResource(R.drawable.a12);
						break;
					case 13:
						is = getResources().openRawResource(R.drawable.a13);
						break;
					case 14:
						is = getResources().openRawResource(R.drawable.a14);
						break;
					case 15:
						is = getResources().openRawResource(R.drawable.a15);
						break;
					case 16:
						is = getResources().openRawResource(R.drawable.a16);
						break;
					case 17:
						is = getResources().openRawResource(R.drawable.a17);
						break;
					case 18:
						is = getResources().openRawResource(R.drawable.a18);
						break;
					case 19:
						is = getResources().openRawResource(R.drawable.a19);
						break;
					case 20:
						is = getResources().openRawResource(R.drawable.a20);
						break;
					}

					OutputStream os = new FileOutputStream(file);
					byte[] data = new byte[is.available()];
					is.read(data);
					os.write(data);
					is.close();
					os.close();

					showToast(mContext, "Image saved in your 'Pictures' folder.");
					MediaScannerConnection
							.scanFile(
									mContext,
									new String[] { file.toString() },
									null,
									new MediaScannerConnection.OnScanCompletedListener() {
										@Override
										public void onScanCompleted(
												String path, Uri uri) {
											Log.i("ExternalStorage", "Scanned "
													+ path + ":");
											Log.i("ExternalStorage", "-> uri="
													+ uri);
										}
									});
				} else {
					String text = "Error during saving..";
					showToast(mContext, text);
				}
			} catch (IOException e) {
				String text = "Error during saving...";
				showToast(mContext, text);
			}
		}
//////////////////////////////hari_end_savefuncion
////		System.out.println("rand");
//		
//		rand = new Random();
////		System.out.println("rand"+rand);
//		imageNumber = rand.nextInt(pics.length);
////		System.out.println("imageNumber"+imageNumber);
//		image = BitmapFactory.decodeResource(res, pics[imageNumber]);
////		System.out.println("image "+image);
//
//		physicsInitialized = false;
	}

	public void showToast(Context cont, String message) {
		if (toast == null) {
			toast = Toast.makeText(cont, message, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
		}
		if (!toast.getView().isShown()) {
			toast.setText(message);
			toast.show();
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		if (!hasWindowFocus)
			thread.pause();
	}

	public void setButton(Button button) {
		mSaveButton = button;
	}

	public void setTextView(TextView textView) {
		mStatusText = textView;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		thread.setSurfaceSize(width, height);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		if (thread.getState() == Thread.State.TERMINATED) {
			thread = new PuzzleThread(getHolder(), mContext, getHandler());
		}

		thread.setRunning(true);
		thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		thread.setRunning(false);
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		synchronized (getThread().getSurfaceHolder()) {
			if (mStatusText.isShown()) {
				mStatusText.setVisibility(INVISIBLE);
				return false;
			} else {
				int newx = (int) event.getX();
				int newy = (int) event.getY();


				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					movingPiece = false;
//					System.out.println("-----------------1======"+bitmapWd3);
//					System.out.println("-----------------1======"+bitmapHd3);
					if (newx < bitmapWd3) {
						if (newy < bitmapHd3) {
							currPieceOnTouch = 0;
						} else if (newy < bitmapHd3 * 2) {
							currPieceOnTouch = 3;
						} else {
							currPieceOnTouch = 6;
						}
						System.out.println("============1--------------"+bitmapWd3);
						System.out.println("-----------------1======"+bitmapHd3);
					} else if (newx < bitmapWd3 * 2) {
						if (newy < bitmapHd3) {
							currPieceOnTouch = 1;
						} else if (newy < bitmapHd3 * 2) {
							currPieceOnTouch = 4;
						} else {
							currPieceOnTouch = 7;
						}
						System.out.println("============2--------------"+bitmapWd3);
						System.out.println("-----------------2======"+bitmapHd3);
					} else if (newx < bitmapWd3 * 3) {
						if (newy < bitmapHd3) {
							currPieceOnTouch = 2;
						} else if (newy < bitmapHd3 * 2) {
							currPieceOnTouch = 5;
						} else {
							currPieceOnTouch = 8;
						}
						System.out.println("============3--------------"+bitmapWd3);
						System.out.println("-----------------3======"+bitmapHd3);
//						0 3 6
//						1 4 7
//						2 5 8
//
//						0 4 8  12
//						1 5 9  13
//						2 6 10 14
//						3 7 11 15
					}
				}

				if (event.getAction() == MotionEvent.ACTION_UP) {
					movingPiece = false;
//					System.out.println("-----------------11======"+bitmapWd3);
//					System.out.println("-----------------11======"+bitmapHd3);
					if (newx < bitmapWd3) {
						if (newy < bitmapHd3) {
							currSlotOnTouchUp = 0;
						} else if (newy < bitmapHd3 * 2) {
							currSlotOnTouchUp = 3;
						} else {
							currSlotOnTouchUp = 6;
						}

						System.out.println("-----------------w=11======"+bitmapWd3);
						System.out.println("-----------------h=11======"+bitmapHd3);
					} else if (newx < bitmapWd3 * 2) {
						if (newy < bitmapHd3) {
							currSlotOnTouchUp = 1;
						} else if (newy < bitmapHd3 * 2) {
							currSlotOnTouchUp = 4;
						} else {
							currSlotOnTouchUp = 7;
						}
						System.out.println("----------------w=22======"+bitmapWd3);
						System.out.println("----------------h=22======"+bitmapHd3);
					} else if (newx < bitmapWd3 * 3) {
						if (newy < bitmapHd3) {
							currSlotOnTouchUp = 2;
						} else if (newy < bitmapHd3 * 2) {
							currSlotOnTouchUp = 5;
						} else {
							currSlotOnTouchUp = 8;
						}
						System.out.println("============-----w--33-------"+bitmapWd3);
						System.out.println("---------------h--33======"+bitmapHd3);
					}

					if (currPieceOnTouch != currSlotOnTouchUp) {
						sendPieceToNewSlot(currPieceOnTouch, currSlotOnTouchUp);

						int a = jumbled.get(currSlotOnTouchUp);
						int z = jumbled.get(currPieceOnTouch);
						jumbled.set(currSlotOnTouchUp, z);
						jumbled.set(currSlotOnTouchUp, a);

						solved = true;
						for (int i = 0; i < numberOfPieces; i++) {
							if (puzzleSlots[i].slotNum != puzzleSlots[i].puzzlePiece.pieceNum) {
								solved = false;
								System.out.println("-----------------numberOfpieces======"+numberOfPieces);
							}
						}

						if (solved) {
							mSaveButton.setVisibility(VISIBLE);
						}

					} else {
						puzzleSlots[currPieceOnTouch].puzzlePiece.px = puzzleSlots[currSlotOnTouchUp].sx;
						puzzleSlots[currPieceOnTouch].puzzlePiece.py = puzzleSlots[currSlotOnTouchUp].sy;
					}
				}

				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					movingPiece = true;
					if (currPieceOnTouch >= 0 || currPieceOnTouch <= 8) {
						puzzleSlots[currPieceOnTouch].puzzlePiece.px = newx
								- bitmapWd3 / 2;
						puzzleSlots[currPieceOnTouch].puzzlePiece.py = newy
								- bitmapHd3 / 2;
					}
				}
			}
			return true;
		}
	}

	public void sendPieceToNewSlot(int a, int z) {
		PuzzlePiece temp = new PuzzlePiece();
		temp = puzzleSlots[currPieceOnTouch].puzzlePiece;
		puzzleSlots[a].puzzlePiece = puzzleSlots[z].puzzlePiece;
		puzzleSlots[a].puzzlePiece.px = puzzleSlots[a].sx;
		puzzleSlots[a].puzzlePiece.py = puzzleSlots[a].sy;
		puzzleSlots[z].puzzlePiece = temp;
		puzzleSlots[z].puzzlePiece.px = puzzleSlots[z].sx;
		puzzleSlots[z].puzzlePiece.py = puzzleSlots[z].sy;
		temp = null;
	}
}
