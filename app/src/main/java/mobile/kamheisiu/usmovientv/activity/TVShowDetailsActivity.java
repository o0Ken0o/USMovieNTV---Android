package mobile.kamheisiu.usmovientv.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mobile.kamheisiu.usmovientv.R;
import mobile.kamheisiu.usmovientv.data.model.TVShowDetails;
import mobile.kamheisiu.usmovientv.databinding.ActivityTvshowDetailsBinding;
import mobile.kamheisiu.usmovientv.viewmodel.TVShowDetailsActivityViewModel;

public class TVShowDetailsActivity extends AppCompatActivity {

    private TVShowDetailsActivityViewModel viewModel;
    private ActivityTvshowDetailsBinding binding;
    private int mTVShowId;
    public static final String TV_SHOW_ID_KEY = "TV_SHOW_ID_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tvshow_details);

        Intent intent = getIntent();
        if (intent != null) {
            mTVShowId = intent.getIntExtra(TV_SHOW_ID_KEY, -1);
        }

        binding.progressSpinner.setVisibility(View.VISIBLE);
        binding.contentScrollView.setVisibility(View.INVISIBLE);

        viewModel = new TVShowDetailsActivityViewModel(TVShowDetailsActivity.this, mTVShowId);
        viewModel.getIsDataReady().subscribe(this::displayData);
    }

    public void displayData(Boolean isDataReady) {
        if (isDataReady) {
            binding.setTvshowdetailsvm(viewModel);
            binding.contentScrollView.setVisibility(View.VISIBLE);
        }

        binding.progressSpinner.setVisibility(View.GONE);
    }
}
