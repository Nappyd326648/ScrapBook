package com.example.in0418gq.scrapbook;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by in0418gq on 10/18/16.
 */
public class InspirationListFragment extends Fragment {

    private RecyclerView mInspirationRecyclerView;
    private InspirationAdapter mAdapter;

    //adding menu to the fragment
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void  onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.search_inspiration,menu);
        //ToDO add edit text  for search
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view =  inflater.inflate(R.layout.fragment_inspiration_list,container,false);
        mInspirationRecyclerView = (RecyclerView) view.findViewById(R.id.inspiration_recycler_view);
        mInspirationRecyclerView.setLayoutManager(new LinerLayoutManager(getActivity()));

        updateUI();
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }
    private void  updateUI(){
        InspirationLab inspirationLab = InspirationLab.get(getActivity());
        List<Inspiration> inspirations = inspirationLab.getInspirations();
        if (mAdapter == null){
            mAdapter = new InspirationAdapter(inspirations);
            mInspirationRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }
    public class InspirationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mDateTextView;

        private Inspiration mInspiration;

        public InspirationHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.);
            mDateTextView = (TextView) itemView.findViewById(R.id.);

        }

        public void bindInspiration(Inspiration inspiration) {
            mInspiration = inspiration;
            mTitleTextView.setText(mInspiration.getTitle());
            mDateTextView.setText(mInspiration.getDate().toString());

        }

        @Override
        public void onClick(View v) {
            Intent intent = InspirationPagerActivity.newIntent(getActivity(), mInspiration.getId());
            startActivity(intent);
        }
    }

    private class InspirationAdapter extends RecyclerView.Adapter<InspirationHolder> {

        private List<Inspiration> mInspiration;

        public InspirationAdapter(List<Inspiration> inspirations) {
            mInspiration = inspirations;
        }

        @Override
        public InspirationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.fragment_inspiration_list, parent, false);
            return new InspirationHolder(view);
        }

        @Override
        public void onBindViewHolder(InspirationHolder holder, int position) {
            Inspiration inspiration  = mInspiration.get(position);
            holder.bindInspiration(inspiration);

        }

        @Override
        public int getItemCount() {
            return mInspiration.size();
        }



    }

}
