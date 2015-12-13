package com.minecraftpe.doubleplus;
import android.support.v4.app.*;
import android.content.*;

public class MppFragmentPagerAdapter extends FragmentPagerAdapter {
	public final int COUNT = 2;
	private String[] titles = new String[]{"新闻", "团队"};
	private Context context;
	public MppFragmentPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		this.context = context;
	}
	@Override
	public Fragment getItem(int position) {
		return PageFragment.newInstance(position + 1);
	}
	@Override
	public int getCount() {
		return COUNT;
	}
	@Override
	public CharSequence getPageTitle(int position) {
		return titles[position];
	}


}
