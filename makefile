JCC = javac

default: OnlineMarketModel.class OnlineMarketView.class OnlineMarketController.class OnlineMarket.class

OnlineMarketModel.class: OnlineMarketModel.java
	$(JCC) OnlineMarketModel.java

OnlineMarketView.class: OnlineMarketView.java
	$(JCC) OnlineMarketView.java

OnlineMarketController.class: OnlineMarketController.java
	$(JCC) OnlineMarketController.java

OnlineMarket.class: OnlineMarket.java
	$(JCC) OnlineMarket.java

clean:
	$(RM) *.class
