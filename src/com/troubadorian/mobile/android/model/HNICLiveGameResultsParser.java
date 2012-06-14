/**
 * Author : Arunabh Das
 */

package com.troubadorian.mobile.android.model;



public class HNICLiveGameResultsParser
{

    // private static Logger logger = Logger.getLogger(HNICLiveGameResultsParser.class);

    private HNICGame currentGame;

    private HNICEvent currentEvent;

    private HNICGameResults gameResults = HNICGameResults.getInstance();
    /*
     * public Document parse(String url) { SAXBuilder builder = new
     * SAXBuilder(); Document doc = null; try { doc = builder.build(new
     * URL(url)); } catch (MalformedURLException e) { // TODO Auto-generated
     * catch block e.printStackTrace(); } catch (JDOMException e) { // TODO
     * Auto-generated catch block e.printStackTrace(); } catch (IOException e) {
     * // TODO Auto-generated catch block e.printStackTrace(); } return doc; }
     * 
     * public void processDocument(Document doc) { org.jdom.Element root =
     * doc.getRootElement(); List<Element> nodes = root.getChildren("dict");
     * Iterator<Element> iter = nodes.iterator(); while (iter.hasNext()) {
     * Element gameNode = iter.next(); Game game = getGame(gameNode);
     * logger.debug(gameNode.toString()); gameResults.addGame(game); }
     * logger.debug(nodes); }
     * 
     * public static void main(String[] args) { BasicConfigurator.configure();
     * logger.debug("Starting Live Parser"); LiveGameResultsParser lParser = new
     * LiveGameResultsParser(); Document doc = lParser.parse(
     * "file:///Users/warmannj/Documents/data/statsfeed/plist.old/gameresult.plist"
     * ); lParser.processDocument(doc); }
     * 
     * private Game getGame(Element gameNode) { return new Game(); }
     * 
     * private GameResults getGameResults(Element resultsNode) { return
     * GameResults.getInstance(); }
     */
}