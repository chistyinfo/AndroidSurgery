package androidsurgery.greendustbd.androidsurgery.TechInfoVolley;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import androidsurgery.greendustbd.androidsurgery.AppController;
import androidsurgery.greendustbd.androidsurgery.R;


public class CustomTeachInfoListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Teachinfo> bookItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomTeachInfoListAdapter(AppCompatActivity appCompatActivity, List<Teachinfo> bookItems) {
		this.activity = appCompatActivity;
		this.bookItems = bookItems;

	}

	@Override
	public int getCount() {
		return bookItems.size();
	}

	@Override
	public Object getItem(int location) {
		return bookItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_tips_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView summary = (TextView) convertView.findViewById(R.id.summary);



		// getting movie data for the row
		Teachinfo m = bookItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
		
		// Title/Name
		title.setText(m.getTitle());
		
		// summary
		summary.setText("summary: " + String.valueOf(m.getSummary()));





		return convertView;
	}

}