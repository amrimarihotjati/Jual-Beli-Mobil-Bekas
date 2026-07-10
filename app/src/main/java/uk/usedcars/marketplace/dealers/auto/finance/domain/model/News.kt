package uk.usedcars.marketplace.dealers.auto.finance.domain.model

data class NewsResponse(
    val status: String,
    val feed: FeedInfo?,
    val items: List<NewsItem>?
)

data class FeedInfo(
    val title: String,
    val link: String
)

data class NewsItem(
    val title: String,
    val pubDate: String,
    val link: String,
    val thumbnail: String,
    val description: String,
    val author: String
)
