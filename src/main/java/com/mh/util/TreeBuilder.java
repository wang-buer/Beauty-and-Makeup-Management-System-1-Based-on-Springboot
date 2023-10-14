package com.mh.util;


import com.mh.model.Menu;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
* @author xukh
* @date 2019/12/3 0003 10:36
*/
public class TreeBuilder {


    List<Menu > nodes = new ArrayList<>();

    public String buildTree(List<Menu > nodes) {

        TreeBuilder treeBuilder = new TreeBuilder(nodes);

        return treeBuilder.buildJSONTree();
    }

    public TreeBuilder() {
    }

    public TreeBuilder(List<Menu > nodes) {
        super();
        this.nodes = nodes;
    }

    // 构建JSON树形结构
    public String buildJSONTree() {

        List<Menu > nodeTree = buildTree();
        JSONArray jsonArray = JSONArray.fromObject(nodeTree);
        return jsonArray.toString();
    }

    // 构建树形结构
    public List<Menu > buildTree() {

        List<Menu > treeNodes = new ArrayList<>();
        List<Menu > rootNodes = getRootNodes();
        for (Menu  rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    // 递归子节点
    public void buildChildNodes(Menu  node) {

        List<Menu > children = getChildNodes(node);
        if (!children.isEmpty()) {
            for (Menu  child : children) {
                buildChildNodes(child);
            }
            node.setChild(children);
        }
    }

    // 获取父节点下所有的子节点

    public List<Menu > getChildNodes(Menu  pnode) {
        List<Menu > childNodes = new ArrayList<>();
        for (Menu  n : nodes) {
            if (pnode.getId().equals(n.getPid())) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }

    // 判断是否为根节点
    public boolean rootNode(Menu  node) {
        boolean isRootNode = true;
        for (Menu  n : nodes) {
            if (node.getPid().equals(n.getId())) {
                isRootNode = false;
                break;
            }
        }
        return isRootNode;
    }

    // 获取集合中所有的根节点
    public List<Menu > getRootNodes() {
        List<Menu > rootNodes = new ArrayList<>();
        for (Menu  n : nodes) {
            if (rootNode(n)) {
                rootNodes.add(n);
            }
        }
        return rootNodes;
    }
}
