#include <iostream>
#include <cstdlib>
#include <string>
#include "AVL.h"
using namespace std;
int conversion(string s)
{
	return atoi(s.c_str());
}
int main()
{
	string In;
	AVL * X = new AVL();
	while(true)
	{
		cin>>In;
		if(In[0]=='p' || In[0]=='q')
		{
			if(In[0]=='p') X->presentation(); else break;
		}
		else
		{
			int value = conversion(In);
			if(X->search(value)==NULL) X->add(value); else X->remove(value);
			X->stablize(X->getRoot());
		}
	}
	X->~AVL();
	return 0;
}