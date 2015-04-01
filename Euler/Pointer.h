#include <cstdlib>
#ifndef POINTER_H
#define POINTER_H
#include "Node.h"
template<class N>
class Pointer
{
private:
	N * pointer;
	Pointer<N> * next;
public:
	Pointer(N * t)
	{
		pointer=t;
		next=NULL;
	}
	~Pointer()
	{
		if(next!=NULL) next->~Pointer();
	}
	N * getNode()
	{
		return pointer;
	}
	Pointer<N> * getNext()
	{
		return next;
	}
	void setNext(Pointer<N> * t)
	{
		next=t;
	}
	bool areMultipleConnection()
	{
		if(next->getNode()==this->getNode()) return true; return false;
	}
};
#endif