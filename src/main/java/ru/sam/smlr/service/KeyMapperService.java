package ru.sam.smlr.service;

/**
 * Created by msamichev on 30.03.2017.
 */
public interface KeyMapperService {
    interface Add {
        class Success implements Add {
            private String key;
            private String link;

            public Success(String key, String link) {
                this.key = key;
                this.link = link;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Success success = (Success) o;

                if (key != null ? !key.equals(success.key) : success.key != null) return false;
                return link != null ? link.equals(success.link) : success.link == null;
            }

            @Override
            public int hashCode() {
                int result = key != null ? key.hashCode() : 0;
                result = 31 * result + (link != null ? link.hashCode() : 0);
                return result;
            }
        }

        class AlreadyExist implements Add {
            private String key;

            public AlreadyExist(String key) {
                this.key = key;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                AlreadyExist that = (AlreadyExist) o;

                return key != null ? key.equals(that.key) : that.key == null;
            }

            @Override
            public int hashCode() {
                return key != null ? key.hashCode() : 0;
            }
        }
    }

    interface Get {
        class Link implements Get {
            private String link;

            public Link(String link) {
                this.link = link;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Link link1 = (Link) o;

                return link != null ? link.equals(link1.link) : link1.link == null;
            }

            @Override
            public int hashCode() {
                return link != null ? link.hashCode() : 0;
            }

            public String getLink() {
                return link;
            }
        }

        class NotFound implements Get {
            private String key;

            public NotFound(String key) {
                this.key = key;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                NotFound notFound = (NotFound) o;

                return key != null ? key.equals(notFound.key) : notFound.key == null;
            }

            @Override
            public int hashCode() {
                return key != null ? key.hashCode() : 0;
            }
        }
    }

    Add add(String key, String link);

    Get getLink(String key);
}
