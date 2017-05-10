import React from 'react'
import cssModules from 'react-css-modules'
import Drawer from "material-ui/Drawer"
import RaisedButton from "material-ui/RaisedButton"
import Snackbar from 'material-ui/Snackbar'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'
import AppBar from 'material-ui/AppBar'
import style from './explore.style.scss'

@cssModules(style, {errorWhenNotFound: false})
export class ExploreView extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      open: false

    }
  }

  handleToggle = () => this.setState({open: !this.state.open})
  handleClose = () => this.setSate({open: false})


  render() {
    return (
      <section id="pricing" styleName="pricing-section">
        <MuiThemeProvider>
          <AppBar
            title="WAE"
            iconClassNameRight="muidocs-icon-navigation-expand-more"
            style={{
              backgroundColor: 'rgb(37, 47, 62)',
              position: 'fixed'
            }}
          >
            <MuiThemeProvider>
              <div>
                <RaisedButton
                  label="Click Here"
                  onTouchTap={this.handleToggle}
                  style={{marginTop: '20px'}}
                />
                <Snackbar
                  open={this.state.open}
                  message="Event added to your calendar"
                  autoHideDuration={10000}
                  onRequestClose={this.handleRequestClose}
                  action="undo"
                />
                {/*<Drawer*/}
                  {/*docked={false}*/}
                  {/*width={300}*/}
                  {/*open={this.state.open}*/}
                  {/*openSecondary={true}*/}
                  {/*onRequestChange={(open) => this.setState({open})}*/}
                {/*>*/}
                  {/*<AppBar*/}
                    {/*title="WAE"*/}
                    {/*iconClassNameRight="muidocs-icon-navigation-expand-more"*/}
                    {/*style={{*/}
                      {/*marginTop: '40px',*/}
                      {/*width: '500px'*/}
                    {/*}}*/}
                  {/*/>*/}
                {/*</Drawer>*/}
              </div>
            </MuiThemeProvider>
          </AppBar>
        </MuiThemeProvider>
        <section styleName="pricing-section--upper">
          <article styleName="pricing-section__intro-article">
            <h1 styleName="article__header">plans built for every one</h1>
            <p styleName="article__paragraph">Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
              Aenean
              commodo
              ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis
              parturient montes, nascetur ridiculus mus.
            </p>
          </article>
        </section>
        <section styleName="pricing-section--intermediate">
          <article styleName="pricing-section__article--vertical">
            <h1 styleName="vertical-article__header">Starter</h1>
            <span styleName="pricing-section__price-tag">
                <span styleName="icon-money">$</span><span styleName="price-tag">9</span>/Month
            </span>
            <p styleName="vertical-article__content--main">
              Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo
              ligula eget dolor. Aenean massa
            </p>
            <button styleName="vertical-article__button--default">Select Plan</button>
          </article>
          <article styleName="pricing-section__article--vertical">
            <h1 styleName="vertical-article__header">Starter</h1>
            <span styleName="pricing-section__price-tag">
                <span styleName="icon-money">$</span><span styleName="price-tag">9</span>/Month
            </span>
            <p styleName="vertical-article__content--main">
              Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo
              ligula eget dolor. Aenean massa
            </p>
            <button styleName="vertical-article__button--default">Select Plan</button>
          </article>
          <article styleName="pricing-section__article--vertical">
            <h1 styleName="vertical-article__header">Starter</h1>
            <span styleName="pricing-section__price-tag">
                <span styleName="icon-money">$</span><span styleName="price-tag">9</span>/Month
            </span>
            <p styleName="vertical-article__content--main">
              Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo
              ligula eget dolor. Aenean massa
            </p>
            <button styleName="vertical-article__button--default">Select Plan</button>
          </article>
          <article styleName="pricing-section__article--vertical">
            <h1 styleName="vertical-article__header">Starter</h1>
            <span styleName="pricing-section__price-tag">
                <span styleName="icon-money">$</span><span styleName="price-tag">9</span>/Month
            </span>
            <p styleName="vertical-article__content--main">
              Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo
              ligula eget dolor. Aenean massa
            </p>
            <button styleName="vertical-article__button--default">Select Plan</button>
          </article>
        </section>
        <section styleName="pricing-section--lower">
          <p styleName="paragraph--lower">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean
            commodo
            ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis
            parturient montes, nascetur ridiculus mus.<span styleName="color--red"> Learn More.</span></p>
        </section>
      </section>
    )
  }
}
