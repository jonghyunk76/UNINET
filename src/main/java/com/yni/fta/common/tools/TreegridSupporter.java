package com.yni.fta.common.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kr.yni.frame.util.StringHelper;

/**
 * 
 * @author jonghyunkim
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class TreegridSupporter {
	
	private static Log log = LogFactory.getLog(TreegridSupporter.class);
	
	/**
     * <p>
     * <strong>TreeHelper</strong>의 default 컨스트럭터(Constructor).
     * </p>
     */
    public TreegridSupporter() { }
    
    /**
     * <p>
     * 메뉴의 트리 구조를 받아온다.(메뉴에 한정해서 사용)<br>
     * 단, 메뉴정보에는 다음과 같은 속성자를 포함하고 있어야 한다.<br>
     *   1) ID : 고유식별자<br>
     *   2) TEXT : 화면에 표시될 명칭<br>
     *   3) LIKE_URL : TEXT 클릭 시 이동한 경로<br>
     * </p>
     * @param treeList = 메뉴 리스트
     * @return
     * @throws Exception
     */
    public static List getMenuData(List treeList) throws Exception {
    	return getMenuData(treeList, "PARENT_ID", "ID");
    }
    
    /**
     * <p>
     * 메뉴의 트리 구조를 받아온다.(메뉴에 한정해서 사용)<br>
     * 단, 메뉴정보에는 다음과 같은 속성자를 포함하고 있어야 한다.<br>
     *   1) ID : 고유식별자<br>
     *   2) TEXT : 화면에 표시될 명칭<br>
     *   3) LIKE_URL : TEXT 클릭 시 이동한 경로<br>
     * </p>
     * @param treeList = 메뉴 리스트
     * @param parent = parent key
     * @param child = child key
     * @return
     * @throws Exception
     */
    public static List getMenuData(List treeList, String parent, String child) throws Exception{
        
        List resultList = new ArrayList();
        
        for(int i=0; i < treeList.size(); i++){
            Map treeMap = (Map)treeList.get(i);
            Map menuMap = getMenuInfo(treeList, treeMap, parent, child);
            
            if(menuMap != null && !menuMap.isEmpty()) {
                resultList.add(menuMap);
            }
        }
        return resultList;
    }
    
    /**
     * 메뉴의 parent - child 구조를 만들어 준다(3level 까지만 적용됨,메뉴에 한정해서 사용)
     * 
     * @param treeList = 메뉴 리스트
     * @param treeMap = 메뉴정보
     * @param parent = parent key
     * @param child = child key
     * @param treeMap
     * @return
     * @throws Exception
     */
    private static Map getMenuInfo(List treeList, Map treeMap, String parent, String child) throws Exception{
        List array02 = null;
        List array03 = null;
        
        // 1레벨
        if(treeMap.get(parent) == null){
            array02 = new ArrayList();
            
            int cnt_01 = 0;
            // 2 레벨
            for(int i=0; i < treeList.size(); i++){
                Map menuMap2 = (Map)treeList.get(i);
                if(menuMap2.get(parent) !=  null && menuMap2.get(parent).equals(treeMap.get(child))){
                    
                    array03 = new ArrayList();
                    int cnt_02 = 0;
                    
                    // 3레벨
                    for(int j=0; j < treeList.size(); j++){
                        Map menuMap3 = (Map)treeList.get(j);
                        if(menuMap3.get(parent) !=  null && menuMap3.get(parent).equals(menuMap2.get(child))){
                            array03.add(createMenuMap(menuMap3, parent));
                            
                            cnt_02++;
                        }
                    }
                    
                    // 2레벨 하위에 3레벨이 있다면...
                    if(cnt_02 > 0) {
                        menuMap2.put("children", array03);
                    }
                    
                    array02.add(createMenuMap(menuMap2, parent));
                    cnt_01++;
                }
                
            }
            
            // 2레벨 하위에 3레벨이 있다면...
            if(cnt_01 > 0) {
                treeMap.put("children", array02);
            }
            return createMenuMap(treeMap, parent);
        } else {
            return new HashMap();
        }
    }
    
    /**
     * <p>
     * menu에 한정된 특정 값 셋팅(getDataJson 에서 사용)
     * </p>
     * @param testMap
     * @return
     * @throws Exception
     */
    private static Map createMenuMap(Map testMap, String parent) throws Exception{
        Map jsonMap = new LinkedHashMap();
        Map attributeMap = new LinkedHashMap();
        
        jsonMap.put("id", testMap.get("ID"));
        jsonMap.put("text", testMap.get("TEXT"));
        
        if(testMap.get("LINK_URL")!=null){
            attributeMap.put("url",  testMap.get("LINK_URL"));
            attributeMap.put("parent_id",  testMap.get(parent));
            jsonMap.put("attributes", attributeMap);
        }
        
        if(testMap.get("children") != null){
            jsonMap.put("children", testMap.get("children"));
            jsonMap.put("state", "open");
        }
        
        return jsonMap;
    }
    
    /**
     * <p>
     * 트리데이터 구조를 만들어 준다.(parent - child) 형태(level 제한 없음)
     * 단, Tree 정보에는 다음과 같은 속성자를 포함하고 있어야 한다.<br>
     *   1) ID : 고유식별자<br>
     *   2) TEXT : 화면에 표시될 명칭<br>
     *   3) LIKE_URL : TEXT 클릭 시 이동한 경로<br>
     * </p>
     * @param treeList = 조회된 tree data
     * @param parent = parent key
     * @param child = child key
     * @return
     * @throws Exception
     * 예제 
     * 		ArrayList allTreeList = new ArrayList();
     *      resultList = TreeDataHandler.createTreeData(resultList, "PARENT_ID", "ID");
     */
	public static List getTreeData(List treeList, String parent, String child) throws Exception {
    	ArrayList allTreeList = new ArrayList();
    	
    	return getTreeData(treeList, 0, parent, child, allTreeList, null, null, null, 1, -1, null);
    }
    
    /**
     * <p>
     * 트리데이터 구조를 만들어 준다.(parent - child) 형태(level 제한 없음)
     * 단, Tree 정보에는 다음과 같은 속성자를 포함하고 있어야 한다.<br>
     *   1) ID : 고유식별자<br>
     *   2) TEXT : 화면에 표시될 명칭<br>
     *   3) LIKE_URL : TEXT 클릭 시 이동한 경로<br>
     * </p>
     * 
     * @param treeList = 조회된 tree data
     * @param parent = parent key Column
     * @param child = child key Column
     * @param collapse_level = closed level
     * @return
     * @throws Exception
     * 예제 
     * 		ArrayList allTreeList = new ArrayList();
     *      resultList = TreeDataHandler.createTreeData(resultList, 0, "PARENT_ID", "ID", 2);
     */
	public static List getTreeData(List treeList, String parent, String child, int collapse_level) throws Exception {
    	ArrayList allTreeList = new ArrayList();
    	
    	return getTreeData(treeList, 0, parent, child, allTreeList, null, null, null, 1, collapse_level, null);
    }
    
    /**
     * <p>
     * 트리데이터 구조를 만들어 준다.(parent - child) 형태(level 제한 없음)
     * 단, Tree 정보에는 다음과 같은 속성자를 포함하고 있어야 한다.<br>
     *   1) ID : 고유식별자<br>
     *   2) TEXT : 화면에 표시될 명칭<br>
     *   3) LIKE_URL : TEXT 클릭 시 이동한 경로<br>
     * </p>
     * 
     * @param treeList = 조회된 tree data 
     * @param startIdx = start index
     * @param parent = parent key Column
     * @param child = child key Column
     * @return
     * @throws Exception
     * 예제 
     * 		ArrayList allTreeList = new ArrayList();
     *      resultList = TreeDataHandler.createTreeData(resultList, 0, "PARENT_ID", "ID");
     */
	public static List getTreeData(List treeList, int startIdx, String parent, String child) throws Exception {
    	ArrayList allTreeList = new ArrayList();
    	
    	return getTreeData(treeList, startIdx, parent, child, allTreeList, null, null, null, 1, -1, null);
    }
    
	/**
     * <p>
     * 트리데이터 구조를 만들어 준다.(parent - child) 형태(level 제한 없음)
     * 단, Tree 정보에는 다음과 같은 속성자를 포함하고 있어야 한다.<br>
     *   1) ID : 고유식별자<br>
     *   2) TEXT : 화면에 표시될 명칭<br>
     *   3) LIKE_URL : TEXT 클릭 시 이동한 경로<br>
     * </p>
     * 
     * @param treeList = 조회된 tree data 
     * @param startIdx = start index
     * @param parent = parent key Column
     * @param child = child key Column
     * @param sortList = 정렬된 트리 List
     * @return
     * @throws Exception
     * 예제 
     * 		ArrayList allTreeList = new ArrayList();
     *      resultList = TreeDataHandler.createTreeData(resultList, 0, "PARENT_ID", "ID");
     */
	public static List getTreeList(List treeList, String parent, String child, List sortList) throws Exception {
		ArrayList allTreeList = new ArrayList();
		
    	getTreeData(treeList, 0, parent, child, allTreeList, null, null, null, 1, -1, sortList);
    	
    	return sortList;
    }
	
    /**
     * <p>
     * 트리데이터 구조를 만들어 준다.(parent - child) 형태(level 제한 없음)
     * 단, Tree 정보에는 다음과 같은 속성자를 포함하고 있어야 한다.<br>
     *   1) ID : 고유식별자<br>
     *   2) TEXT : 화면에 표시될 명칭<br>
     *   3) LIKE_URL : TEXT 클릭 시 이동한 경로<br>
     * </p>
     * 
     * @param treeList = 조회된 tree data 
     * @param startIdx = start index
     * @param parent = parent key Column
     * @param child = child key Column
     * @param collapse_level = closed level
     * @return
     * @throws Exception
     * 예제 
     * 		ArrayList allTreeList = new ArrayList();
     *      resultList = TreeDataHandler.createTreeData(resultList, 0, "PARENT_ID", "ID", 2);
     */
	public static List getTreeData(List treeList, int startIdx, String parent, String child, int collapse_level) throws Exception {
    	ArrayList allTreeList = new ArrayList();
    	
    	return getTreeData(treeList, startIdx, parent, child, allTreeList, null, null, null, 1, collapse_level, null);
    }
    
    /**
     * 트리데이터 구조를 만들어 준다.(parent - child) 형태(level 제한 없음)
     * 
     * 재귀호출을 사용하여 최하위단까지 추적한다. (bom 전개 or tree menu에서 사용)
     * 아래 parameter 정의에서 무저건 null or 0 이라고 되어있는 변수 들은 재귀호출시 값을 만들어서 넘겨줌
     * @param treeList = 조회된 tree data
     * @param startIdx = start index
     * @param parent = parent key Column
     * @param child = child key Column
     * @param allTreeList = 트리구조로 만들어질 최종 treeList(new 객체를 생성해서 넘겨줌)
     * @param childCode = 재귀호출시 부모가 되는 child Code(무조건 null)
     * @param curMap = child 객체를 담는 map(무조건 null)
     * @param call = 재귀호출인지 여부 판단(무조건 null)
     * @param collapse_level = closed level
     * @param sortList = 정렬된 트리 List
     * @return
     * @throws Exception
     * 예제 
     * 		ArrayList allTreeList = new ArrayList();
     *      resultList = TreeDataHandler.createTreeData(resultList, 0, "PARENT_ID", "ID", allTreeList, null, null, null, null);
     */
	private static List getTreeData(List treeList, int startIdx, String parent, String child, ArrayList allTreeList, 
			String childCode, Map curMap, String call, int level, int collapse_level, List sortList) throws Exception {
    	ArrayList childList= new ArrayList();
    	
    	if(curMap == null){
			curMap = new HashMap();
		}

		for(int i=startIdx; i < treeList.size(); i++) {
			Map tMap = (Map)treeList.get(i);
			
			String leaf = null;
			boolean collapse = false;
			String parentValue = StringHelper.null2void(tMap.get(parent));
			String childValue = StringHelper.null2void(tMap.get(child));
			
			if((parentValue.isEmpty() || parentValue.equals(childValue)) && call == null) {
				curMap = new HashMap();
				childList= new ArrayList();
				curMap = tMap;
				
				for(int t = 0 ; t < treeList.size(); t++) {
					if(StringHelper.null2void(((Map)treeList.get(t)).get(parent)).equals(childValue)) {
						leaf = "N";
						break;
					}
				}
				
				if("N".equals(leaf) && collapse_level != -1 && level >= collapse_level) {
					collapse = true;
				}
				
				allTreeList.add(createTreeMap(curMap, collapse));
				if(sortList != null) sortList.add(tMap);
				
				childCode = (childValue == null) ? "" : childValue.toString();
				
				log.debug("1 ==> parent : " + parentValue + " / child : " + childCode + " / leaf = " + leaf + " / depth = " + (level));
				
				level = 1;
			} else {
				if(parentValue != null && parentValue.equals(childCode)) {
					if(curMap.get("children") != null) {
						childList = (ArrayList)curMap.get("children");
					}
					
					for(int t = 0 ; t < treeList.size(); t++) {
						if(StringHelper.null2void(((Map)treeList.get(t)).get(parent)).equals(childValue)) {
							leaf = "N";
							break;
						}
					}
					
					if(!parentValue.isEmpty()) {
						tMap.put("_parentId", parentValue);
					}
					
					log.debug("2 ==> parent's value : " + parentValue + " / child's value = " + childValue + " / leaf = " + leaf + " / depth = " + (level+1) + " / _parentId = " + parentValue);
					
					if("N".equals(leaf) && collapse_level != -1 && (level+1) >= collapse_level) {
						collapse = true;
					}
					
					if(sortList != null) sortList.add(tMap);
					childList.add(createTreeMap(tMap, collapse));
					curMap.put("children", childList);
					
					getTreeData(treeList, i+1, parent, child, allTreeList, childValue, tMap, "reflection", level+1, collapse_level, sortList);
				}
			}
		}

		return allTreeList;
	}
    
    /**
     * Tree 내 단일 정보를 저장하는 Map을 생성한다.
     * 
     * @param tMap
     * @return
     * @throws Exception
     */
	private static Map createTreeMap(Map tMap, boolean collapse) throws Exception{
        Map attributeMap = new LinkedHashMap();
        String id = StringHelper.null2void(tMap.get("ID"));
        String text = StringHelper.null2void(tMap.get("TEXT"));
        String url = StringHelper.null2void(tMap.get("LINK_URL"));
        
//        if(!id.isEmpty()) {
//        }
        
        tMap.put("id", id);
        tMap.put("text", text);
        tMap.put("url",  url);
        
//        for (Iterator it = tMap.entrySet().iterator(); it.hasNext();) {
//			Map.Entry entry = (Map.Entry) it.next();
//			String key = (String) entry.getKey();
//			if(tMap.get(key) != null){
//				attributeMap.put(key,  tMap.get(key));
//			}
//		}
//        
//        tMap.put("attributes", attributeMap);
        
        if(collapse) {
        	tMap.put("state", "closed");
        }
        
        return tMap;
    }
	
}
