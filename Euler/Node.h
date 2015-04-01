#include <cstdlib>
#ifndef NODE_H
#define NODE_H
#include "Neighborhood.h"
#include "Pointer.h"
class Node
{
public:
	Node()
	{
		numberOfVisits=0;
		list = new Neighborhood  <Node, Pointer <Node> >();
	}
	~Node()
	{
		if(list!=NULL) list->~Neighborhood();
	}
	void setNeighborhood(Node * listOfNodes [], int lenght)
	{
		for(int i=0; i<lenght; i++)
		{
			list->addNodeToList(listOfNodes[i]);
		}
		numberOfVisits=list->getLenght();
	}
	void setID(int id)
	{
		ID=id;
	}
	int getID()
	{
		return ID;
	}
	Neighborhood < Node, Pointer <Node> > * getList()
	{
		return list;
	}
	bool trackViaNodeComplete()
	{
		if(numberOfVisits==0) return true; return false;
	}
	void visitNode()
	{
		numberOfVisits--;
	}
private:
	int numberOfVisits;
	Neighborhood < Node, Pointer <Node> > * list;
	int ID;
};
#endif
