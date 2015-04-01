#include <cstdlib>
#include <string>
#include <iostream>
#include "Graph.h"
using namespace std;
int main()
{
	int I;
	cin>>I;
	int ** t = new int * [I];
	/*
	string path;
	cin>>path;
	FILE * f = fopen(path.c_str,"r");
	char q = getc(f);
	string lenght="";
	while(q!='\n')
	{
		lenght+=q;
		q = getc(f);
	}
	I=atoi(lenght.c_str);
	string data="";
	*/
	for(int i=0; i<I; i++) {t[i]=new int [I];}
	for(int i=0; i<I; i++)
	{
		for(int j=0; j<I; j++)
		{
			/*
			data="";
			q = getc(f);
			while(q!=' ' && q!='\n')
			{
				data+=q;
				q = getc(f);
			}
			t[i][j]=atoi(data.c_str);
			*/
			cin>>t[i][j];
		}
	}

	Graph * graph = new Graph(I);
	for(int i=0; i<I; i++)
	{
		int lenght = 0;
		for(int j=0; j<I; j++)
		{
			lenght+=t[i][j];
		}
		Node ** ta = new Node * [lenght];
		int l=0;
		for(int j=0; j<I; j++)
		{
			for(int k=0; k<t[i][j]; k++)
			{
				ta[l]=graph->getNode(j);
				l++;
			}
		}
		graph->setNeighborhood(i,ta,lenght);
	}

	graph->execute();
	system("Pause");
	return 0;
}