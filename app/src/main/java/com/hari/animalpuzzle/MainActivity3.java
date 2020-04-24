package com.hari.animalpuzzle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.hari.animalpuzzle.DrawingView.PuzzleThread;
import com.hari.animalpuzzle.activity.MainActivity;

public class MainActivity3 extends Activity {
	
    private static final int MENU_EA = 1,MENU_PAUSE = 2,MENU_RESUME = 3,MENU_START = 4,MENU_STOP = 5,MENU_SAVE=6;
	String getURL,URL,bottomURL;
	View topAdd, bottomAdd, _full_image_view, _rl_puzzle; Handler handler;
	ImageButton _btn_viewimage, _btn_mainmenu;
	TextView _text;
	final int []imageAddArray={R.drawable.banner_ucweb,R.drawable.banner_olx,R.drawable.banner_freeapps,R.drawable.banner_topandroid,R.drawable.banner_dealslelo,R.drawable.banner_flipcart};
	final int []imageBottomArray={R.drawable.banner_freeapps,R.drawable.banner_topandroid,R.drawable.banner_dealslelo,R.drawable.banner_olx,R.drawable.banner_flipcart,R.drawable.banner_ucweb};

	private DrawingView.PuzzleThread puzzleThread;
    int viewImage;
    private DrawingView puzzleView;
//    int imagenum=0;
    boolean count=false;
    private int[] pics = { R.drawable.a0, R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10, R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15, R.drawable.a16, R.drawable.a17, R.drawable.a18, R.drawable.a19,  R.drawable.a20,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main3);
        
//    System.out.println("imgnum"+imagenum);
        puzzleView = (DrawingView) findViewById(R.id.puzzle);
        puzzleThread = puzzleView.getThread();
        puzzleView.setTextView((TextView) findViewById(R.id.text));
        puzzleView.setButton((Button) findViewById(R.id.saveButton));
        viewImage=puzzleView.imageNumber;
        System.out.println("---------------------anutag----------//"+viewImage);
///////////////////hari_banner
        
        if (savedInstanceState == null) {
        	puzzleThread.setState(PuzzleThread.STATE_READY);
            Log.w(this.getClass().getName(), "Saved instance state is null");
        } else {
        	puzzleThread.restoreState(savedInstanceState);
            Log.w(this.getClass().getName(), "Saved instance state is nonnull");
        }   

//----------------------hhari-----------
topAdd=(View)findViewById(R.id.topBanner);

bottomAdd=(View)findViewById(R.id.bottomBanner);

		handler = new Handler();
		
       Runnable runnable = new Runnable() {
         int j=0;
         public void run() {
      	   topAdd.setBackgroundResource(imageAddArray[j]);
             bottomAdd.setBackgroundResource(imageBottomArray[j]);
          	if(j==0){
         		URL="https://play.google.com/store/apps/details?id=com.UCMobile.intl&referrer=utm_source%3Dtnj%2540hariom-CASH";
         		bottomURL="http://freeappstore.in/";
         	}else if(j==1){
         		URL="http://hasoffers.ymtrack.com/aff_c?offer_id=12422&aff_id=14788";
         		bottomURL="http://appmyfriend.com/";
         	}else if(j==2){
         		URL="http://freeappstore.in/";
         		bottomURL="http://www.dealslelo.com/";
         	}else if(j==3){
         		URL="http://appmyfriend.com/";
         		bottomURL="http://hasoffers.ymtrack.com/aff_c?offer_id=12422&aff_id=14788";
     		}else if(j==4){
     			URL="http://www.dealslelo.com/";
     			bottomURL="http://yeahmobi.go2cloud.org/aff_c?offer_id=15692&aff_id=14788";
     			
     		}else if(j==5){
     			URL="http://yeahmobi.go2cloud.org/aff_c?offer_id=15692&aff_id=14788";
           		bottomURL="https://play.google.com/store/apps/details?id=com.UCMobile.intl&referrer=utm_source%3Dtnj%2540hariom-CASH";
           	}
          	j++;
          	if(j>imageBottomArray.length-1){
          	
          		j=0;
          	}
              handler.postDelayed(this, 6000);  //for interval...
          	System.out.println("--------j----//"+j);
          }
      };	            	
      handler.postDelayed(runnable, 500); //for initial delay..
//_________harisep
      
      topAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Context context=v.getContext();
      		Intent thingyToInstall=new Intent(Intent.ACTION_VIEW);
      		thingyToInstall.setDataAndType(Uri.parse(URL), null);
      		context.startActivity(thingyToInstall);
			}
		});
      
    bottomAdd.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Context context=v.getContext();
   		Intent thingyToInstall=new Intent(Intent.ACTION_VIEW);
   		thingyToInstall.setDataAndType(Uri.parse(bottomURL), null);
   		context.startActivity(thingyToInstall);
		}
	});
    
    _text=(TextView)findViewById(R.id.text);

    _rl_puzzle=(View)findViewById(R.id.rl_puzzle);

    _full_image_view=(View)findViewById(R.id.full_image_view);

    _btn_viewimage = (ImageButton)findViewById(R.id.btn_viewimage);
    _btn_viewimage.setOnClickListener(new OnClickListener() {
  		@Override
  		public void onClick(View v) {
//  			count=true;
  	        int imagenum = puzzleView.imageNumber;
  	  	_full_image_view.setBackgroundResource(pics[imagenum]);
  	      System.out.println("-------------imgnum"+imagenum);
  			
                if(count==false){
                	
                	_text.setVisibility(View.GONE);
                	_full_image_view.setVisibility(View.VISIBLE);
                	_rl_puzzle.setVisibility(View.GONE);
                	count=true;
                }
                else if(count==true){

                	_text.setVisibility(View.VISIBLE);
                	_full_image_view.setVisibility(View.GONE);
                	_rl_puzzle.setVisibility(View.VISIBLE);
                	count=false;
                }
	       }
  			
  	});
    _btn_mainmenu = (ImageButton)findViewById(R.id.btn_mainmenu);
    _btn_mainmenu.setOnClickListener(new OnClickListener() {
  		
  		@Override
  		public void onClick(View v) {
  			puzzleThread.setState(PuzzleThread.STATE_LOSE,
  			      getText(R.string.message_stopped));

  			Intent intent=new Intent(getApplicationContext(), MainActivity.class);
  		      startActivity(intent);
//		      _btn_mainmenu.setVisibility(Button.GONE);
  		}
  	});
//  	----------------------hhari-----------
  	        //for initial delay..

//////////////endbanner
////////hari_ex////////////////////////////
    
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
//        menu.add(0, MENU_EA, 0, R.string.menu_ea_SPO);
//        menu.add(0, MENU_PAUSE, 0, R.string.menu_pause);
//        menu.add(0, MENU_RESUME, 0, R.string.menu_resume);
//        menu.add(0, MENU_START, 0, R.string.menu_start);
//        menu.add(0, MENU_STOP, 0, R.string.menu_stop);
        menu.add(0, MENU_SAVE, 0, R.string.menu_save);
        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_START:
            	
                puzzleThread.doStart();
                return true;
            case MENU_STOP:
            	puzzleThread.setState(PuzzleThread.STATE_LOSE,
                        getText(R.string.message_stopped));
                return true;
            case MENU_PAUSE:
            	puzzleThread.pause();
                return true;
            case MENU_RESUME:
            	puzzleThread.unpause();
                return true;
            case MENU_EA:
            	puzzleThread.setdif(PuzzleThread.DIF_EAS);
                return true;
            case MENU_SAVE:
            	puzzleView.saveImage();
                return true;
        }

        return false;
    }
    
    public void saveImage(View view) {
    	puzzleView.saveImage();
    }
    public void showBtnForNextLevel(View view) {
    	puzzleView.showBtnForNextLevel();
    }
    @Override
    protected void onPause() {
        super.onPause();
        puzzleView.getThread().pause(); // pause game when Activity pauses
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // just have the View's thread save its state into our Bundle
        super.onSaveInstanceState(outState);
        puzzleThread.saveState(outState);
        Log.w(this.getClass().getName(), "SIS called");
    } 
//    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
//    	super.onBackPressed();
    	
//    	puzzleThread.setState(PuzzleThread.STATE_LOSE,
//                getText(R.string.message_stopped));
////    	puzzleThread.run();
//    	puzzleThread.destroy();
//    	puzzleThread.stop();
//        return true;

//			Intent intent=new Intent(getApplicationContext(), MainActivity.class);
//		      startActivity(intent);finish();

    }

}
