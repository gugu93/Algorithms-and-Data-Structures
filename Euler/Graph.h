#include <string>
#include <iostream>
#ifndef GRAPH_H
#define GRAPH_H
#include "Node.h"

using namespace std;

class Graph
{
public:
	Graph(int sizeOfGraph)
	{
		answer1="";
		answer2="";
		size = sizeOfGraph;
		nodes = new Node * [sizeOfGraph];
		for(int i=0; i<sizeOfGraph; i++)
		{
			nodes[i] = new Node();
			nodes[i]->setID(i);
		}
	}
	~Graph()
	{
		for(int i=0; i<size; i++)
		{
			nodes[i]->~Node();
		}
	}
	int getSize();
	void execute();
	Node * getNode(int id);
	void setNeighborhood(int targetID, Node * listOfId [],int lenght);
	Node * cycleStart()
	{
		for(int i=0; i<size; i++)
		{
			if(nodes[i]->getList()->twoDiff()) return nodes[i];
		}
		return NULL;
	}
	Node * roadStart()
	{
		for(int i=0; i<size; i++)
		{
			if(nodes[i]->getList()->getLenght()%2==1) return nodes[i];
		}
		return NULL;
	}
	char getT(int t)
	{
		t+=65;
		return t;
	}
private:
	bool EulerCycle();
	void EulerRoad();
	bool isBridge(Node * c, Node * t);
	Node * makeProperDirection(Node * current, Node * next);
	std::string answer1;
	std::string answer2;
	int size;
	Node ** nodes;
};
int Graph::getSize()
{
	return size;
}
Node * Graph::getNode(int id)
{
	return nodes[id];
}
void Graph::setNeighborhood(int targetID, Node * listOfId [],int lenght)
{
	nodes[targetID]->setNeighborhood(listOfId,lenght);
}
void Graph::execute()
{
	if(EulerCycle())
	{
		std::cout<<answer1<<std::endl;
	}
	else
	{
		EulerRoad();
		std::cout<<answer1<<std::endl;
		std::cout<<answer2<<std::endl;
	}
}
bool Graph::EulerCycle()
{
	int i=-1;
	bool exist = true;
	while(++i<size)
	{
		if(this->nodes[i]->getList()->getLenght()%2!=0 || this->nodes[i]->getList()->getLenght()==0) {exist=false; break;} 
	}
	if(exist)
	{
		answer1="Cykl E. ";
		Node * current = cycleStart();
		Node * minimum;
		answer1+=getT(current->getID());
		do
		{
			minimum = current->getList()->findMin();
			minimum = makeProperDirection(current,minimum);
			current->getList()->remove(minimum->getID());
			minimum->getList()->remove(current->getID());
			current->visitNode();
			minimum->visitNode();
			current=minimum;
			answer1+=getT(current->getID());
		}
		while(!current->trackViaNodeComplete());
	}
	else
	{
		bool x=true;
		answer1="Brak cyklu E. ";
		i=-1;
		while(++i<size)
		{
			if(this->nodes[i]->getList()->getLenght()%2!=0)
			{
				answer1+="d("; answer1+=getT(i); answer1+=") ";
			}
			if(this->nodes[i]->getList()->getLenght()==0)
			{
				answer1="Brak cyklu E. Graf niespójny";
				x=false;
				break;
			}
		}
		if(x)answer1+="- nieparzyste";
	}
	return exist;
}
void Graph::EulerRoad()
{
	int i=-1;
	int numberOfN=0;
	bool y=true;
	while(++i<size)
	{
		if(this->nodes[i]->getList()->getLenght()%2!=0) {numberOfN++;}
		if(this->nodes[i]->getList()->getLenght()==0)
		{
			y=false;
		}
	}
	if(numberOfN==2 && y)
	{
		answer2="Droga E. ";
		Node * current = roadStart();
		Node * minimum;
		answer2+=getT(current->getID());
		do
		{
			minimum = current->getList()->findMinD();
			minimum = makeProperDirection(current,minimum);
			current->getList()->remove(minimum->getID());
			minimum->getList()->remove(current->getID());
			current->visitNode();
			minimum->visitNode();
			current=minimum;
			answer2+=getT(current->getID());
		}
		while(!current->trackViaNodeComplete());
	}
	else
	{
		answer2="Brak drogi E. ";
		i=-1;
		bool x=true;
		while(++i<size)
		{
			if(this->nodes[i]->getList()->getLenght()%2!=0)
			{
				answer2+="d("; answer2+=getT(i); answer2+=") ";
			}
			if(this->nodes[i]->getList()->getLenght()==0)
			{
				answer2="Brak drogi E. Graf niespójny";
				x=false;
				break;
			}
		}
		if(x) answer2+="- nieparzyste";
	}
}
Node * Graph::makeProperDirection(Node * current, Node * next)
{
	if(current->getList()->getLenght()>2)
	{
		Node * temp = current->getList()->findMinU();
		while(isBridge(current,temp))
		{
			temp = current->getList()->findMinK(temp);
		}
		next=temp;
	}
	return next;
}
bool Graph::isBridge(Node * c, Node * t)
{
	if(c->getList()->getLenght()==1) return false;
	else
	{
		if(c->getList()->search(t->getID())->areMultipleConnection()) return false;
		Neighborhood < Node, Pointer <Node> > * temp = new Neighborhood < Node, Pointer <Node> >();
		Pointer<Node> * tempTemp = t->getList()->getFirst();
		while(tempTemp!=NULL)
		{
			if(tempTemp->getNode()!=c && tempTemp->getNode()!=t) temp->addNodeToList(tempTemp->getNode());
			tempTemp=tempTemp->getNext();
		}
		Pointer<Node> * tempStart = temp->getFirst();
		Pointer<Node> * tempEnd = temp->getLast();
		while(true) 
		{
			Pointer<Node> * tempWalk = tempStart;
			while(tempWalk!=NULL)
			{
				if(tempWalk->getNode()->getList()->search(c->getID())!=NULL) return false;
				tempWalk=tempWalk->getNext();
			}
			tempWalk=temp->getFirst();
			while(tempWalk!=tempEnd->getNext())
			{
				tempTemp = tempWalk->getNode()->getList()->getFirst();
				while(tempTemp!=NULL)
				{
					if(temp->search(tempTemp->getNode()->getID())==NULL && tempTemp->getNode()!=c && tempTemp->getNode()!=t) temp->addNodeToList(tempTemp->getNode());
					tempTemp=tempTemp->getNext();
				}
				tempWalk=tempWalk->getNext();
			}
			if(tempEnd==temp->getLast()) return true;
			tempStart=tempEnd->getNext();
			tempEnd=temp->getLast();
		}
		temp->~Neighborhood();
	}
}
#endif
