#ifndef NODE_H
#define NODE_H
using namespace std;
class Node
{
public:
	Node()
	{}
	Node(int val)
	{
		this->left=NULL;
		this->right=NULL;
		this->value=val;
	}
	~Node()
	{
		if(this->left!=NULL) this->left->~Node();
		if(this->right!=NULL) this->right->~Node();
	}
	Node *left;
	Node *right;
	int value;
	void printHorizontally(int pauses);
	void printInOrder();
	void printPostOrder();
	void printPreOrder();
};
void Node::printInOrder()
{
	if(this->left!=NULL) this->left->printInOrder();
	cout<<this->value<<" ";
	if(this->right!=NULL) this->right->printInOrder();
}
void Node::printPostOrder()
{
	if(this->left!=NULL) this->left->printPostOrder();
	if(this->right!=NULL) this->right->printPostOrder();
	cout<<this->value<<" ";
}
void Node::printPreOrder()
{
	cout<<this->value<<" ";
	if(this->left!=NULL) this->left->printPreOrder();
	if(this->right!=NULL) this->right->printPreOrder();
}
void Node::printHorizontally(int pauses)
{
	int leftMargin = 1;
	int tempPauses=pauses;
	while(tempPauses>0)
	{
		leftMargin+=tempPauses;
		leftMargin++;
		tempPauses-4;
	}
	for(int i=0; i<leftMargin; i++)
	{
		cout<<" ";
	}
	cout<<this->value;
	for(int i=0; i<leftMargin; i++)
	{
		cout<<" ";
	}
}
#endif