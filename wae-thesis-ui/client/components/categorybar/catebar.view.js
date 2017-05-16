import React from "react"
import cssModules from "react-css-modules"
import style from "./catebar.style.scss"

import { CateBarElement } from "./catebar.element"
import data from "./catebar.data"

@cssModules(style, {errorWhenNotFound: false})
export class CateBarView extends React.Component {
  render() {
    return (
      <div>
        <CateBarElement path="/app/product/food" header="Food & Grocery"/>
        <CateBarElement path="/app/product/electronic" header="Electronics and TVs"/>
        <CateBarElement path="/app/product/handmade" header="Handmade Delivery"/>
        {
          data.map(category => <CateBarElement header={category} />)
        }
      </div>
    )
  }
}
