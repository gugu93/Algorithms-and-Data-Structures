#include "Node.h"
#include <math.h>
#ifndef AVL_H
#define AVL_H
class AVL
{
public:
	AVL()
	{
		root=NULL;
	}
	~AVL()
	{
		if(root!=NULL) root->~Node();
	}
	void add(int value);
	void remove(int value);
	void presentation();
	void stablize(Node * rootT);
	Node * search(int value);
	Node * parent(int value);
	Node * getRoot();
private:
	Node * nextInOrder(Node * node,int & lvl);
	void printHAG(Node * node, int lvl, int pauses);
	void printHorizontallyAsGraph();
	void del(Node * target);
	void Right(Node * withUnbalancedChildrens);
	void Left(Node * withUnbalancedChildrens);
	Node * childrenWithHigherTree(Node * node);
	int heightOfTree(Node * rootT);
	Node * root;
};
Node * AVL::getRoot()
{
	return root;
}
void AVL::stablize(Node *rootT)
{

	int l=0; int r=0;
	if(rootT->left!=NULL) l = heightOfTree(rootT->left);
	if(rootT->right!=NULL) r = heightOfTree(rootT->right);
	if(abs(r-l)>1)
	{
		if(l>r)
		{
			stablize(rootT->left);
			Left(rootT);
		}
		else
		{
			stablize(rootT->right);
			Right(rootT);
		}
	}
}
void AVL::del(Node *target)
{
	
	Node * temp = childrenWithHigherTree(target);
	Node * p = parent(target->value);
	if(p==NULL) 
	{
			root=temp;
	}
	else
	{
			if(p->right==target) p->right=temp; else p->left=temp;
	}
	if(temp!=NULL)
	{
		
		if(target->left!=NULL && target->right!=NULL)
		{
			Node * temp2;
			if(target->left==temp)
			{
				temp2=temp->right;
				temp->right=target->right;
				temp=temp->right;
				while(temp->left!=NULL) temp=temp->left;
				temp->left=temp2;
			}  
			else
			{
				temp2=temp->left;
				temp->left=target->left;
				temp=temp->left;                                                            
				while(temp->right!=NULL) temp=temp->right;  
				temp->right=temp2;  
			}
		}
	}
	if(target==root) root=NULL;
	target->left=NULL;
	target->right=NULL;   
	target->~Node();                                              
}
void AVL::add(int value)
{
	if(root==NULL)
	{
		root = new Node(value);
	}
	else
	{
		Node *temp = parent(value);
			if(value>temp->value)
			{
				temp->right= new Node(value);
			}
			else
			{
				temp->left = new Node(value);
			}
	}
}
Node * AVL::parent(int value)
{
	Node * temp = root;
	Node * prev = NULL;
	while(temp!=NULL && temp->value!=value)
	{
		if(value>temp->value)
		{
			prev=temp;
			temp=temp->right;
		}
		else
		{
			prev=temp;
			temp=temp->left;
		}
	}
	return prev;
}
void AVL::remove(int value)
{
	del(search(value));
}
Node * AVL::search(int value)
{
	Node *temp = root;
	while(temp!=NULL && temp->value!=value)
	{
		if(value<temp->value)
		{
			temp=temp->left;
		}
		else
		{
			temp=temp->right;
		}
	}
	return temp;
}
void AVL::presentation()
{
	if(root!=NULL) root->printPreOrder();
	//dostêpne s¹ jescze wypisania printPostOrder(), printInOrder(), grafika siê pêtli i nie wypisuje pustych elementów
}
void AVL::Left(Node * withUnbalancedChildrens)
{
	Node * prev = parent(withUnbalancedChildrens->value);
	bool side = false;
	if(prev!=NULL && prev->left==withUnbalancedChildrens) side=true;
	Node * temp = withUnbalancedChildrens->left;
	withUnbalancedChildrens->left = temp->right;
	temp->right=withUnbalancedChildrens;
	if(prev!=NULL)
	{
		if(side) prev->left=temp; else prev->right=temp;
	}
	else root=temp;
}
Node * AVL::childrenWithHigherTree(Node * target)
{
	if(target->left==NULL && target->right==NULL)
	{
		return NULL;
	}
	else
	{
		if(target->left!=NULL && target->right!=NULL)
		{
			if(heightOfTree(target->left)>heightOfTree(target->right)) return target->left;
			else return target->right;
		}
		else
		{
			if(target->left==NULL) return target->right; else return target->left;
		}
	}
}
void AVL::Right(Node * withUnbalancedChildrens)
{
	Node * prev = parent(withUnbalancedChildrens->value);
	bool side = false;
	if(prev!=NULL && prev->left==withUnbalancedChildrens) side=true;
	Node * temp = withUnbalancedChildrens->right;
	withUnbalancedChildrens->right = temp->left;
	temp->left=withUnbalancedChildrens;
	if(prev!=NULL)
	{
		if(side) prev->left=temp; else prev->right=temp;
	}
	else root=temp;
}
int AVL::heightOfTree(Node * rootT)
{
	if(rootT->left==NULL && rootT->right==NULL)
	{
		return 1;
	}
	if(rootT->left!=NULL && rootT->right!=NULL) 
	{
		if(heightOfTree(rootT->left)>heightOfTree(rootT->right)) return heightOfTree(rootT->left)+1; else return heightOfTree(rootT->right)+1;
	}
	else
	{
		if(rootT->left==NULL) return heightOfTree(rootT->right)+1;
		else return heightOfTree(rootT->left)+1;
	}
}
void AVL::printHorizontallyAsGraph()
{
	cout<<endl;
	int verticalSizeOfGraph = heightOfTree(root);
	int numberOfOnSidePauses = (verticalSizeOfGraph-1)*3+(verticalSizeOfGraph-2);
	printHAG(root,0,numberOfOnSidePauses);
}
void AVL::printHAG(Node * node, int lvl, int pauses)
{
	Node * next = nextInOrder(node,lvl);
	node->printHorizontally(pauses);
	if(next!=NULL) 
	{
		printHAG(next,lvl,pauses-4);
	}
	else
	{
		if(next==NULL && heightOfTree(root)>lvl)
		{
				int leftMargin = 1;
				int tempPauses=pauses;
				while(tempPauses>0)
				{
					leftMargin+=tempPauses;
					leftMargin++;
					tempPauses-4;
				}
				for(int i=0; i<leftMargin*2; i++)
				{
					cout<<" ";
				}
				cout<<"   ";
		}
	}
}
Node * AVL::nextInOrder(Node * node, int & lvl)
{
	Node * p = parent(node->value);
	int i =0 ;

	if(p!=NULL)
	{
		while(p->right==NULL)
		{
			p = parent(p->value);
			i++;
		}
		p=p->right;
		if(p!=node)
		{
			while(p->left!=NULL && i>0) 
			{
				p = p->left;
				i--;
			}
		}
	}
	if(p==NULL || p==node)
	{
		lvl++;
		cout<<endl;
		Node * newStart = root;
		for(int i=0; i<lvl; i++)
		{
			if(newStart->left!=NULL)
			{
				newStart=newStart->left;
			}
			else
			{
				newStart=newStart->right;
			}
		}
		p=newStart;
	}
	return p;
}
#endif