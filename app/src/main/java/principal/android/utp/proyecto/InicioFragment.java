package principal.android.utp.proyecto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class InicioFragment extends Fragment {
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.carrousel1, R.drawable.carrousel2, R.drawable.carrousel3, R.drawable.carrousel4};
    String[] sampleNetworkImageURLs = {
            "http://192.241.166.108/sistemacolegio/assets/img/slider/slider_01.jpg",
            "http://192.241.166.108/sistemacolegio/assets/img/slider/slider_02.jpg",
            "http://192.241.166.108/sistemacolegio/assets/img/slider/slider_03.jpg"
    };
    private OnFragmentInteractionListener mListener;

    public InicioFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        };

        carouselView.setImageListener(imageListener);

        return view;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
