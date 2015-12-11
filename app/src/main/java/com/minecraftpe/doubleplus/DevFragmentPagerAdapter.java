package com.minecraftpe.doubleplus;
import android.support.v4.app.*;
import android.content.*;

public class DevFragmentPagerAdapter extends FragmentPagerAdapter {
	public final int COUNT = 2;
	private String[] titles = new String[]{"加盟团队", "加盟项目"};
	private Context context;
	public DevFragmentPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		this.context = context;
	}
	@Override
	public Fragment getItem(int position) {
		return McdevPage.newInstance(position + 1);
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
