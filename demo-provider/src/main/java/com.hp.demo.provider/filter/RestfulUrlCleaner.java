package com.hp.demo.provider.filter;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hp
 * @version 1.0
 * @date 2021/3/22 20:48
 */
public class RestfulUrlCleaner implements UrlCleaner {

    public static final String ROOT_PATH = "/";

    private Set<String> skipSet = new HashSet<>();

    private static final Pattern pathVariable = Pattern.compile("\\{(\\w+)\\}");

    public RestfulUrlCleaner() {
    }

    public RestfulUrlCleaner(Set<String> skipSet) {
        this.skipSet = skipSet;
    }

    /***
     * <p>Process the url. Some path variables should be handled and unified.</p>
     * <p>e.g. collect_item_relation--10200012121-.html will be converted to collect_item_relation.html</p>
     *
     * @param originUrl original url
     * @return processed url
     */
    @Override
    public String clean(String originUrl) {
        return originUrl;
    }

    /***
     *  根据restful接口类型，进行清空
     *  url的格式： /api/{tenantId}/name/{transactionId}
     *  提取变量信息，然后确定哪些应该被替换，当前采用的默认策略是租户编号被替换，而营销订单ID不被替换
     * @param originUrl original url
     * @param pattern 匹配的pattern
     * @return processed url
     */
    public String clean(String originUrl, String pattern) {
        if (originUrl.startsWith(ROOT_PATH)) {
            originUrl = originUrl.substring(1);
        }
        if (pattern.startsWith(ROOT_PATH)) {
            pattern = pattern.substring(1);
        }
        String[] original = originUrl.split(ROOT_PATH);
        String[] patternArray = pattern.split(ROOT_PATH);
        if (original.length != patternArray.length) {
            return originUrl;
        }
        Matcher matcher;
        StringBuilder replacedUrl = new StringBuilder();
        for (int i = 0; i < patternArray.length; i++) {
            replacedUrl.append(ROOT_PATH);
            matcher = pathVariable.matcher(patternArray[i]);
            if (matcher.matches() && skipSet.contains(matcher.group(1))) {
                replacedUrl.append(matcher.group(0));
            } else {
                replacedUrl.append(original[i]);
            }
        }
        return replacedUrl.toString();
    }

    public static void main(String[] args) {

        HashSet<String> set = new HashSet<>();
        //        set.add("tenantId");
        set.add("transactionId");
        //        set.add("activityId");

        RestfulUrlCleaner restfulUrlCleaner = new RestfulUrlCleaner(set);

        String result = restfulUrlCleaner
                .clean("/api/00001234/A1224455/44433344566", "/api/{tenantId}/{activityId}/{transactionId}");

        System.out.println(result);

    }
}
