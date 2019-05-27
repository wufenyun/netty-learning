package com.learning.datastructure.linkedlist;

//评测题目: 编写代码，实现：自定义一个链表数据结构（可以是单链表或双向链表），
//然后初始化一个链表数据，并对该链表实现两两翻转（是翻转整个节点，而不是仅交换节点的值），
//然后输出翻转之后的结果。比如构造的链表是：1->2->3->4->5，翻转之后，输出：2->1->4->3->5.

/**
 *解体思路：
 *1.首先构建一个链表类，具备插入/输出功能
 *2.实现两两翻转
 *  两个相邻节点作为基础数据对，一个数据对位置交换完成之后，
 *  最重要的是要将前一对交换位置的数据对next指针指向当前这一对
 *  
 * @author: wufenyun
 *
 * @date: 2019/3/21
 */
public class LinkedList<T> {
	
	private Entry<T> head;

	/**
	 * 向链表中插入记录的功能
	 *
	 * @param t
	 *            要插入的数据
	 */
	public void add(T t) {
		Entry<T> entry = new Entry(t);
		entry.next = null;
		
		// 首先判断头节点是否为空
		if (null == head) {
			head = entry;
		} else {
			entry.next = head;
			head = entry;
		}
	}
	
	/**
	 * 打印链表功能
	 */
	public void print() {
		Entry<T> cunrrent = head;
		while(cunrrent != null) {
			System.out.println(cunrrent.value);
			cunrrent = cunrrent.next;
		}
	}

	/**
	 * 实现两两翻转功能： 
	 * 循环链表，两个相邻节点作为基础数据对，一个数据对位置交换完成之后，
	 * 最重要的是要将前一对交换位置的数据对next指针指向当前这一对
	 *
	 */
	public void turn() {
		//记录上上一个节点
		Entry<T> pprevNode = null;
		//记录前一个节点
		Entry<T> prevNode = null;
		Entry<T> currentNode = head;
		// 记录当前节点索引
		int index = 0;
		while (null != currentNode) {
			index++;
			if (index % 2 == 0) {
				//对相邻两个节点位置进行交换
				prevNode.next = currentNode.next;
				currentNode.next = prevNode;
				
				if(index == 2) {
					pprevNode = prevNode;
					head = currentNode;
				} else {
					//将前一对交换的节点的next指针指向这一对交换节点的前节点
					//实现两队节点链接起来
					pprevNode.next = currentNode;
					//记录当前节点对的后一个元素，以其next指针指向下一对节点交换位置后的节点
					pprevNode = prevNode;
				}
				currentNode = prevNode.next;
			} else {
				prevNode = currentNode;
				currentNode = currentNode.next;
			}
		}
    }

	public static class Entry<T> {
		private Entry<T> next;
		private T value;

		public Entry(T value) {
			this.value = value;
		}

		public Entry<T> getNext() {
			return next;
		}

		public void setNext(Entry<T> next) {
			this.next = next;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

	}

	public Entry<T> getHead() {
		return head;
	}

	public void setHead(Entry<T> head) {
		this.head = head;
	}
	

	public static void main(String[] args) {
		LinkedList<Integer> li = new LinkedList<Integer>();
		li.add(5);
		li.add(4);
		li.add(3);
		li.add(2);
		li.add(1);
		
		li.print();
		li.turn();
		System.out.println("");
		li.print();
	}

}
