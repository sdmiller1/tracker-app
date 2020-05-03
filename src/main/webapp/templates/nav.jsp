<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand" href="browse">Movie Collector</a>
    <button class="navbar-toggler " type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarToggler">
        <ul class="navbar-nav mr-auto mt-0">
            <li class="nav-item active">
                <a class="nav-link" href="browse">Browse Movies</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="profile">My Profile</a>
            </li>
        </ul>

        <form class="form-inline my-2 my-lg-0 needs-validation" novalidate action="movie" method="get">
            <!-- TODO: this needs to be one line at medium break point and small break point -->
            <input class="form-control mr-sm-2" type="search" placeholder="Search" name="search" required>
            <button class="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>