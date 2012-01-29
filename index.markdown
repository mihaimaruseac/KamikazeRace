---
layout: page
footer: true
---

For now, this is only a test. I really like [GitHub].

It seems that CSS didn't work for the first time. I'm taking bits and bits of
it from various places (mainly Octopress and some Jekyll examples here and
there) and putting them togheter to create something awesome.

Bear with me and think of the question for which the following is true :)

<h1> Answer: {{ site.answer }}</h1>

> quote

    a = a + 2

{% include downloads.html %}
{% include clone.html %}
{% include github.html %}

Extras
======

<h2 id="overriding_styles">Overriding Styles</h2>


<p>If you want to add or override styles, edit <code>sass/custom/_styles.scss</code>. This stylesheet is imported last, so you can override styles with the cascade.</p>

<h2 id="customizing_styles">Changing the Color Scheme</h2>


<p>For help choosing colors check out <a href="http://hslpicker.com">HSL Color Picker</a>, an easy to use web based color picker.</p>

<pre><code># In /sass/base/
_theme.scss      # All colors are defined here

# In /sass/custom/ - Change these files for easy customization
_colors.scss     # Override colors in base/_theme.scss to change color schemes
_styles.scss     # Easly Override any style (last in the cascade)
</code></pre>

<p>All of the colors for Octopress are defined as Sass variables in <code>/sass/base/_theme.scss</code>.
To customize your color scheme edit <code>sass/custom/_colors.scss</code> and override the colors in <code>sass/base/_theme.scss</code>.</p>

<p>The official Octopress site is using the default &#8216;classic&#8217; theme with a few minor color changes to the custom colors file. Take a look at this file and you&#8217;ll see some lines of sass code that have been commented out.</p>

<figure class='code'><figcaption><span>Custom Colors (sass/custom/_colors.scss)</span><a href='https://github.com/imathis/octopress/tree/master/.themes/classic/sass/custom/_colors.scss'>View on Github </a></figcaption> <div class="highlight"><table><tr><td class="gutter"><pre class="line-numbers"><span class='line-number'>1</span>
<span class='line-number'>2</span>
<span class='line-number'>3</span>
<span class='line-number'>4</span>
<span class='line-number'>5</span>
<span class='line-number'>6</span>
</pre></td><td class='code'><pre><code class='scss'><span class='line'><span class="na">$header-bg</span><span class="o">:</span> <span class="mh">#263347</span><span class="p">;</span>
</span><span class='line'><span class="na">$subtitle-color</span><span class="o">:</span> <span class="nf">lighten</span><span class="p">(</span><span class="nv">$header-bg</span><span class="o">,</span> <span class="mi">58</span><span class="p">);</span>
</span><span class='line'><span class="na">$nav-bg</span><span class="o">:</span> <span class="nf">desaturate</span><span class="p">(</span><span class="nf">lighten</span><span class="p">(</span><span class="mh">#8fc17a</span><span class="o">,</span> <span class="mi">18</span><span class="p">)</span><span class="o">,</span> <span class="mi">5</span><span class="p">);</span>
</span><span class='line'><span class="na">$sidebar-bg</span><span class="o">:</span> <span class="nf">desaturate</span><span class="p">(</span><span class="mh">#eceff5</span><span class="o">,</span> <span class="mi">8</span><span class="p">);</span>
</span><span class='line'><span class="na">$sidebar-link-color</span><span class="o">:</span> <span class="nf">saturate</span><span class="p">(</span><span class="mh">#526f9a</span><span class="o">,</span> <span class="mi">10</span><span class="p">);</span>
</span><span class='line'><span class="na">$sidebar-link-color-hover</span><span class="o">:</span> <span class="nf">darken</span><span class="p">(</span><span class="mh">#7ab662</span><span class="o">,</span> <span class="mi">9</span><span class="p">);</span>
</span></code></pre></td></tr></table></div></figure>


<p>The custom colors file has some commented out colors you can use. The theme file is broken up into sections to make it easier to read through. Here&#8217;s a look at the navigation section of <code>sass/base/_theme.scss</code>.</p>

<figure class='code'><figcaption><span>Navigation (sass/base/_theme.scss)</span><a href='https://github.com/imathis/octopress/tree/master/.themes/classic/sass/base/_theme.scss'>View on Github </a></figcaption> <div class="highlight"><table><tr><td class="gutter"><pre class="line-numbers"><span class='line-number'>1</span>
<span class='line-number'>2</span>
<span class='line-number'>3</span>
<span class='line-number'>4</span>
<span class='line-number'>5</span>
</pre></td><td class='code'><pre><code class='scss'><span class='line'><span class="cm">/* Navigation */</span>
</span><span class='line'><span class="na">$nav-bg</span><span class="o">:</span> <span class="mh">#ccc</span> <span class="nv">!default</span><span class="p">;</span>
</span><span class='line'><span class="na">$nav-color</span><span class="o">:</span> <span class="nf">darken</span><span class="p">(</span><span class="nv">$nav-bg</span><span class="o">,</span> <span class="mi">38</span><span class="p">)</span> <span class="nv">!default</span><span class="p">;</span>
</span><span class='line'><span class="na">$nav-color-hover</span><span class="o">:</span> <span class="nf">darken</span><span class="p">(</span><span class="nv">$nav-color</span><span class="o">,</span> <span class="mi">25</span><span class="p">)</span> <span class="nv">!default</span><span class="p">;</span>
</span><span class='line'><span class="nc">...</span>
</span></code></pre></td></tr></table></div></figure>


<p>The <code>!default</code> rule lets the variable be overridden if it is defined beforehand.
is imported before the <code>_theme.scss</code> it can predefine these colors easily. There are comments to help out with this in the
<a href="https://github.com/imathis/octopress/tree/master/.themes/classic/sass/custom/_colors.scss">source</a>.</p>

<p>Many of the colors in the theme are picked using <a href="http://sass-lang.com/docs/yardoc/Sass/Script/Functions.html">Sass&#8217;s color functions</a>.
As a result you can pick a new background color for the navigation by setting the <code>$nav-bg</code> variable
and the other colors will derived for you. This isn&#8217;t perfect, but it should do a decent job with most colors.</p>

<h2 id="changing_layout">Changing the Layout</h2>


<pre><code># In /sass/base
_layout.scss     # Responsive layouts are defined here

# In /sass/custom - Change these files for easy customization
_layout.scss     # Override settings for base/_layout.scss to change the layout
</code></pre>

<p>Just like with colors, widths in <code>/sass/base/_layout.scss</code> are defined like <code>$max-width: 1200px !default;</code> and can be easily customized
by defining them in <code>sass/custom/_layout.scss</code>. Here&#8217;s a look at the layout defaults.</p>

<figure class='code'><figcaption><span>Layout Defaults (_layout.scss)</span><a href='https://github.com/imathis/octopress/tree/master/.themes/classic/sass/base/_layout.scss'>view on Github </a></figcaption> <div class="highlight"><table><tr><td class="gutter"><pre class="line-numbers"><span class='line-number'>1</span>
<span class='line-number'>2</span>
<span class='line-number'>3</span>
<span class='line-number'>4</span>
<span class='line-number'>5</span>
<span class='line-number'>6</span>
<span class='line-number'>7</span>
<span class='line-number'>8</span>
<span class='line-number'>9</span>
<span class='line-number'>10</span>
<span class='line-number'>11</span>
<span class='line-number'>12</span>
<span class='line-number'>13</span>
<span class='line-number'>14</span>
<span class='line-number'>15</span>
</pre></td><td class='code'><pre><code class='scss'><span class='line'><span class="na">$max-width</span><span class="o">:</span> <span class="mi">1200</span><span class="kt">px</span> <span class="nv">!default</span><span class="p">;</span>
</span><span class='line'>
</span><span class='line'><span class="c1">// Padding used for layout margins</span>
</span><span class='line'><span class="na">$pad-min</span><span class="o">:</span> <span class="mi">18</span><span class="kt">px</span> <span class="nv">!default</span><span class="p">;</span>
</span><span class='line'><span class="na">$pad-narrow</span><span class="o">:</span> <span class="mi">25</span><span class="kt">px</span> <span class="nv">!default</span><span class="p">;</span>
</span><span class='line'><span class="na">$pad-medium</span><span class="o">:</span> <span class="mi">35</span><span class="kt">px</span> <span class="nv">!default</span><span class="p">;</span>
</span><span class='line'><span class="na">$pad-wide</span><span class="o">:</span> <span class="mi">55</span><span class="kt">px</span> <span class="nv">!default</span><span class="p">;</span>
</span><span class='line'>
</span><span class='line'><span class="c1">// Sidebar widths used in media queries</span>
</span><span class='line'><span class="na">$sidebar-width-medium</span><span class="o">:</span> <span class="mi">240</span><span class="kt">px</span> <span class="nv">!default</span><span class="p">;</span>
</span><span class='line'><span class="na">$sidebar-pad-medium</span><span class="o">:</span> <span class="mi">15</span><span class="kt">px</span> <span class="nv">!default</span><span class="p">;</span>
</span><span class='line'><span class="na">$sidebar-pad-wide</span><span class="o">:</span> <span class="mi">20</span><span class="kt">px</span> <span class="nv">!default</span><span class="p">;</span>
</span><span class='line'><span class="na">$sidebar-width-wide</span><span class="o">:</span> <span class="mi">300</span><span class="kt">px</span> <span class="nv">!default</span><span class="p">;</span>
</span><span class='line'>
</span><span class='line'><span class="na">$indented-lists</span><span class="o">:</span> <span class="n-Pseudo">false</span> <span class="nv">!default</span><span class="p">;</span>
</span></code></pre></td></tr></table></div></figure>


<p>These variables are used to calculate the width and padding for the responsive layouts. The <code>$indented-lists</code> variable allows you to choose if you prefer indented or normal lists.</p>

[GitHub]: https://github.com/{{ site.GHuser }}/ me

