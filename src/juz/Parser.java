package juz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {

	private static String baseUrl;
	private static PrintWriter out;

	public Parser() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		out = new PrintWriter("price.csv");
		baseUrl = "http://snow-headquarter.ru";
		Connection conn = Jsoup
				.connect("http://snow-headquarter.ru/catalog/women-s-jackets/");
		Document doc = conn.timeout(0).get();
		Element pager = doc.select(".content noindex").first();
		String lastPageHref = pager.select("a").last().attr("href");
		String pageHrefPattern = lastPageHref.substring(1,
				lastPageHref.indexOf("PAGEN_1") + 8);
		System.out.println(pageHrefPattern);
		int lastPageNum = Integer.parseInt(lastPageHref.substring(lastPageHref
				.indexOf("PAGEN_1") + 8));
		String curPageUrl = null;
		
		ArrayList<Good> goodList = new ArrayList<Good>();
		
		for (int curPageNum = 1; curPageNum <= lastPageNum; curPageNum++) {
			curPageUrl = baseUrl +"/"+ pageHrefPattern + curPageNum;
			goodList.addAll(parseCataloguePage(curPageUrl));
		}
		
	}

	private static ArrayList<Good> parseCataloguePage(String catPageUrl) throws IOException {
		// TODO Auto-generated method stub
		Connection conn = Jsoup.connect(catPageUrl);
   		Document doc = conn.timeout(0).get();
   		String goodPageUrl = null;
   		ArrayList<Good> goodList = new ArrayList<Good>();
   		for (Element el: doc.select(".prods section .prod-item")){
   			goodPageUrl = baseUrl + el.select("a").first().attr("href");
   			goodList.addAll(parseGoodPage(goodPageUrl));
		}
   		return goodList;
	}

	private static ArrayList<Good> parseGoodPage(String goodPageUrl) throws IOException {
		// TODO Auto-generated method stub
		Connection conn = Jsoup.connect(goodPageUrl);
   		Document doc = conn.timeout(0).get();
   		Elements rightBlock = doc.select(".prod-page .right");
   		Elements leftBlock = doc.select(".prod-page .left");
   		String imgUrls = null;
   		ArrayList<Good> goodList = new ArrayList<Good>();
   		
   		Good curGood = null;
   		// цикл по блокам разных цветов
   		for( Element rEl:rightBlock.select(".ps-color section li")){
   			// дл€ каждого цвета - создаем отдельную запись
   			curGood = new Good();

   			
   			curGood.setColorId(rEl.attr("data-color-id"));
   			curGood.setModeId(rEl.attr("data-off-id"));
   			curGood.setName(rightBlock.select("h1").first().text());
   			curGood.setColors(rightBlock.select(".description p:contains(÷вет:)").text());
   			curGood.setDescription(rightBlock.select(".description .description-detail section").text().replaceAll(";", ","));
   			curGood.setPrice(rightBlock.select(".description .price").text());
   			curGood.setSizes(rightBlock.select(".ps-size section").text());
   			curGood.setHref(goodPageUrl);
   			for( Element lEl: leftBlock.select("#prod-gallery-"+curGood.getModeId())){
   				// соберем все фотки в одну строку
   				imgUrls = null;
   				for(Element curImg:	lEl.select("li a")){
   					imgUrls += " "+baseUrl+curImg.attr("data-image");
   				}
   				curGood.setImages(imgUrls);
   	   		}
   			goodList.add(curGood);
   			out.println(curGood);
   			out.flush();
   		}
   		return goodList;
   		
   		
   		
   		/*
   		for( Element el: mainBlock.select("[id^=prod-gallery-")){
   			
   		}*/
   		
	}
	
}
