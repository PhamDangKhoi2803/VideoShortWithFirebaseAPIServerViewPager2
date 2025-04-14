package vn.hcmute.bt_firebase.VD1;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vn.hcmute.bt_firebase.R;
import vn.hcmute.bt_firebase.adapter.VideoFirebaseAdapter;
import vn.hcmute.bt_firebase.model.Video;

public class VideoShortWithFireBase extends AppCompatActivity {
    ViewPager2 viewPager2;
    private VideoFirebaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_short_with_fire_base);
        viewPager2 = findViewById(R.id.vpager2);
        getVideos();

    }

    private void getVideos() {
        // ** set database*/
        DatabaseReference mDataBase = FirebaseDatabase.getInstance() .getReference( "videos");
        FirebaseRecyclerOptions<Video> options = new FirebaseRecyclerOptions.Builder<Video>()
                .setQuery(mDataBase, Video.class).build();
        adapter = new VideoFirebaseAdapter(options);
        viewPager2.setOrientation(viewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
