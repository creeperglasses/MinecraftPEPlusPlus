package com.minecraftpe.doubleplus;
import android.support.v4.app.*;

public class ConPageAdapter extends FragmentStatePagerAdapter{
	
	public ConPageAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position){
			case 0:return con_pagea.newInstance();
			case 1:return con_pageb.newInstance();
			case 2:return con_pagec.newInstance();
			default:return con_pagea.newInstance();
		}
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position){
			case 0:return "介绍";
			case 1:return "评论";
			case 2:return "更新记录";
			default:return "介绍";
		}
	}
    }


