#include <cstdlib>
#ifndef NEIGHBORHOOD_H
#define NEIGHBORHOOD_H
template <class N, class P>
class Neighborhood
{
public:
	Neighborhood()
	{
		lenght=0;
		first=NULL;
	}
	~Neighborhood()
	{
		if(first!=NULL) first->~P();
	}
	int getLenght()
	{
		return lenght;
	}
	void addNodeToList(N * a)
	{
		if(first==NULL)
		{
			first=new P(a);
			last=first;
		}
		else
		{
			last->setNext(new P(a));
			last=last->getNext();
		}
		lenght++;
	}
	P * search(int ID)
	{
		P * t = first;
		while(t!=NULL && t->getNode()->getID()!=ID)
		{
			t=t->getNext();
		}
		return t;
	}
	P * searchPrevious(int ID)
	{
		P * t = first;
		while(t->getNext()!=NULL && t->getNext()->getNode()->getID()!=ID)
		{
			t=t->getNext();
		}
		if(first->getNode()->getID()==ID) return NULL;
		return t;
	}
	void remove(int ID)
	{
		P * del = search(ID);
		P * prev = searchPrevious(ID);
		if(prev==NULL)
		{
			first = first->getNext();
			del->setNext(NULL);
			del->~Pointer();
		}
		else
		{
			prev->setNext(del->getNext());
			del->setNext(NULL);
			if(del==last)
			{
				last=prev;
			}
			del->~Pointer();
		}
		lenght--;
	}
	N * findMin()
	{
		P * t = first;
		P * min = t;
		while(t!=last)
		{
			if(t->getNode()->getID()<min->getNode()->getID()) min=t;
			t=t->getNext();
		}
		return min->getNode();
	}
	N * findMinU()
	{
		P * t = first;
		P * min = t;
		while(t!=last)
		{
			if(t->getNode()->getList()->getLenght()<min->getNode()->getList()->getLenght()) min=t;
			t=t->getNext();
		}
		return min->getNode();
	}
	N * findMinK(N * o)
	{
		P * t = first;
		P * min = t;
		while(t!=last)
		{
			if((t->getNode()->getList()->getLenght()<=min->getNode()->getList()->getLenght()) && o!=t->getNode()) min=t;
			t=t->getNext();
		}
		return min->getNode();
	}
	N * findMinD()
	{
		P * t = first;
		P * min = t;
		P * minN=first;
		while(t!=last)
		{
			if(t->getNode()->getID()<min->getNode()->getID()) min=t;
			t=t->getNext();
		}
		if(min->getNode()->getList()->getLenght()==1 && this->getLenght()==1) return min->getNode();
		else
		{
			t=first;
			while(t!=last)
			{
			if(t->getNode()->getID()<minN->getNode()->getID() && minN->getNode()!=min->getNode()) minN=t;
			t=t->getNext();
			}
			return minN->getNode();
		}
	}
	bool twoDiff()
	{
		P * a=first;
		P * b=last;
		while(a!=b)
		{
			if(a->getNode()!=b->getNode()) return true;
			a=a->getNext();
		}
		return false;
	}
	P * getFirst()
	{
		return first;
	}
	P * getLast()
	{
		return last;
	}
private:
	int lenght;
	P * first;
	P * last;
};
#endif